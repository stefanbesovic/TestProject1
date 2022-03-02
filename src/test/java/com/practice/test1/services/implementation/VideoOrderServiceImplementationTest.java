package com.practice.test1.services.implementation;

import com.practice.test1.entities.Playlist;
import com.practice.test1.entities.Video;
import com.practice.test1.entities.VideoOrder;
import com.practice.test1.services.PlaylistService;
import com.practice.test1.services.VideoOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class VideoOrderServiceImplementationTest {

    private PlaylistService playlistService;
    private VideoOrderService testService;

    @BeforeEach
    void setUp() {
        playlistService = mock(PlaylistService.class);
        testService = new VideoOrderServiceImplementation(playlistService);
    }

    @Test
    void givenPlaylistWithVideos_whenChangeIndexOfVideoFromLeftToRight_thenPositionsChanges() {

        //given
        Playlist playlist = new Playlist();
        playlist.setName("Playlist1");
        Video v = Video.builder()
                .name("Video")
                .build();
        Video v1 = Video.builder()
                .name("Video1")
                .build();
        Video v2 = Video.builder()
                .name("Video2")
                .build();
        playlist.getVideos().add(new VideoOrder(playlist, v, 1));
        playlist.getVideos().add(new VideoOrder(playlist, v1, 2));
        playlist.getVideos().add(new VideoOrder(playlist, v2, 3));

        //when
        when(playlistService.getPlaylistById(anyLong())).thenReturn(playlist);
        testService.changeIndexOfVideoInPlaylist(playlist.getId(), v, 3);

        //then
        assertNotNull(playlist.getVideos());
        assertFalse(playlist.getVideos().isEmpty());
        assertEquals(3, playlist.getVideos().get(0).getPosition());
        assertEquals(1, playlist.getVideos().get(1).getPosition());
        assertEquals(2, playlist.getVideos().get(2).getPosition());
    }

    @Test
    void givenPlaylistWithVideos_whenChangeIndexOfVideoFromRightToLeft_thenPositionsChanges() {

        //given
        Playlist playlist = new Playlist();
        playlist.setName("Playlist1");
        Video v = Video.builder()
                .name("Video")
                .build();
        Video v1 = Video.builder()
                .name("Video1")
                .build();
        Video v2 = Video.builder()
                .name("Video2")
                .build();
        playlist.getVideos().add(new VideoOrder(playlist, v, 1));
        playlist.getVideos().add(new VideoOrder(playlist, v1, 2));
        playlist.getVideos().add(new VideoOrder(playlist, v2, 3));

        //when
        when(playlistService.getPlaylistById(anyLong())).thenReturn(playlist);
        testService.changeIndexOfVideoInPlaylist(playlist.getId(), v2, 1);

        //then
        assertNotNull(playlist.getVideos());
        assertFalse(playlist.getVideos().isEmpty());
        assertEquals(2, playlist.getVideos().get(0).getPosition());
        assertEquals(3, playlist.getVideos().get(1).getPosition());
        assertEquals(1, playlist.getVideos().get(2).getPosition());
    }

    @Test
    void givenPlaylistWithVideos_whenChangeIndexOfVideoWhichIsNotInPlaylist_thenThrowException() {

        //given
        Playlist playlist = new Playlist();
        playlist.setName("Playlist1");
        Video v = Video.builder()
                .name("Video")
                .build();
        Video v1 = Video.builder()
                .name("Video1")
                .build();
        Video v2 = Video.builder()
                .name("Video2")
                .build();
        playlist.getVideos().add(new VideoOrder(playlist, v, 1));
        playlist.getVideos().add(new VideoOrder(playlist, v1, 2));
        playlist.getVideos().add(new VideoOrder(playlist, v2, 3));
        Video v3 = Video.builder()
                .name("Video3")
                .build();

        //when
        when(playlistService.getPlaylistById(anyLong())).thenReturn(playlist);
        Exception e = assertThrows(NoSuchElementException.class, () -> {
            testService.changeIndexOfVideoInPlaylist(playlist.getId(), v3, 1);
        });

        //then
        assertNotNull(playlist.getVideos());
        assertFalse(playlist.getVideos().isEmpty());
        assertTrue(e.getMessage().contains(String.format("Can't change index of video in playlist. Video not found: %d", v3.getId())));
    }


    @Test
    void givenPlaylistWithVideos_whenChangeIndexOfVideoPositionOutOfBound_thenThrowException() {

        //given
        Playlist playlist = new Playlist();
        playlist.setName("Playlist1");
        Video v = Video.builder()
                .name("Video")
                .build();
        Video v1 = Video.builder()
                .name("Video1")
                .build();
        Video v2 = Video.builder()
                .name("Video2")
                .build();
        playlist.getVideos().add(new VideoOrder(playlist, v, 1));
        playlist.getVideos().add(new VideoOrder(playlist, v1, 2));
        playlist.getVideos().add(new VideoOrder(playlist, v2, 3));

        //when
        when(playlistService.getPlaylistById(anyLong())).thenReturn(playlist);
        Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
            testService.changeIndexOfVideoInPlaylist(playlist.getId(), v1, 4);
        });

        //then
        assertNotNull(playlist.getVideos());
        assertFalse(playlist.getVideos().isEmpty());
        assertTrue(e.getMessage().contains(String.format("Position out of bounds. Position %d - Actual playlist size %d", 4, playlist.getVideos().size())));
    }

    @Test
    void givenPlaylistWithVideos_whenChangeIndexOfVideoToSamePosition_thenPositionsDoesNotChanges() {

        //given
        Playlist playlist = new Playlist();
        playlist.setName("Playlist1");
        Video v = Video.builder()
                .name("Video")
                .build();
        Video v1 = Video.builder()
                .name("Video1")
                .build();
        Video v2 = Video.builder()
                .name("Video2")
                .build();
        playlist.getVideos().add(new VideoOrder(playlist, v, 1));
        playlist.getVideos().add(new VideoOrder(playlist, v1, 2));
        playlist.getVideos().add(new VideoOrder(playlist, v2, 3));

        //when
        when(playlistService.getPlaylistById(anyLong())).thenReturn(playlist);
        testService.changeIndexOfVideoInPlaylist(playlist.getId(), v, 1);

        //then
        assertNotNull(playlist.getVideos());
        assertFalse(playlist.getVideos().isEmpty());
        assertEquals(1, playlist.getVideos().get(0).getPosition());
        assertEquals(2, playlist.getVideos().get(1).getPosition());
        assertEquals(3, playlist.getVideos().get(2).getPosition());
    }

    @Test
    void givenPlaylistWithVideos_whenSortPlaylistVideosByPosition_thenReturnSorted() {

        //given
        Playlist playlist = new Playlist();
        playlist.setName("Playlist1");
        Video v = Video.builder()
                .name("Video")
                .build();
        Video v1 = Video.builder()
                .name("Video1")
                .build();
        Video v2 = Video.builder()
                .name("Video2")
                .build();
        playlist.getVideos().add(new VideoOrder(playlist, v, 3));
        playlist.getVideos().add(new VideoOrder(playlist, v1, 1));
        playlist.getVideos().add(new VideoOrder(playlist, v2, 2));

        //when
        testService.sortVideos(playlist);

        //then
        assertNotNull(playlist.getVideos());
        assertFalse(playlist.getVideos().isEmpty());
        assertEquals(1, playlist.getVideos().get(0).getPosition());
        assertEquals(2, playlist.getVideos().get(1).getPosition());
        assertEquals(3, playlist.getVideos().get(2).getPosition());
    }

    @Test
    void givenPlaylistAndVideo_whenAddVideoToPlaylist_thenPlaylistWithAddedVideo() {

        //given
        Playlist playlist = new Playlist();
        playlist.setName("Playlist1");
        Video v = Video.builder()
                .name("Video")
                .build();

        //when
        when(playlistService.getPlaylistById(anyLong())).thenReturn(playlist);
        testService.addVideoToPlaylist(playlist.getId(), v);


        //then
        assertNotNull(playlist.getVideos());
        assertFalse(playlist.getVideos().isEmpty());
        assertEquals(v, playlist.getVideos().get(0).getVideo());
    }

    @Test
    void givenPlaylistAndVideo_whenRemoveVideoFromPlaylistWhereVideoIsInPlaylist_thenReturnPlaylistWithoutVideo() {

        //given
        Playlist playlist = new Playlist();
        playlist.setName("Playlist1");
        Video v = Video.builder()
                .name("Video")
                .build();
        playlist.getVideos().add(new VideoOrder(playlist, v, 1));

        //when
        when(playlistService.getPlaylistById(anyLong())).thenReturn(playlist);
        testService.removeVideoFromPlaylist(playlist.getId(), v);

        //then
        assertNotNull(playlist.getVideos());
        assertTrue(playlist.getVideos().isEmpty());
    }

    @Test
    void givenPlaylistAndVideo_whenRemoveVideoFromPlaylistWhereVideoIsNotInPlaylist_thenReturnIntactPlaylist() {

        //given
        Playlist playlist = new Playlist();
        playlist.setName("Playlist1");
        Video v = Video.builder()
                .name("Video")
                .build();
        playlist.getVideos().add(new VideoOrder(playlist, v, 1));
        Video v1 = Video.builder()
                .name("Video1")
                .build();

        //when
        when(playlistService.getPlaylistById(anyLong())).thenReturn(playlist);
        testService.removeVideoFromPlaylist(playlist.getId(), v1);

        //then
        assertNotNull(playlist.getVideos());
        assertFalse(playlist.getVideos().isEmpty());
        assertEquals(v, playlist.getVideos().get(0).getVideo());
    }
}