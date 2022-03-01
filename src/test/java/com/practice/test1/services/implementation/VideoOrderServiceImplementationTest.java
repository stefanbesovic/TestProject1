package com.practice.test1.services.implementation;

import com.practice.test1.entities.Playlist;
import com.practice.test1.entities.Video;
import com.practice.test1.entities.VideoOrder;
import com.practice.test1.services.PlaylistService;
import com.practice.test1.services.VideoOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class VideoOrderServiceImplementationTest {

    @Mock
    private PlaylistService playlistService;

    @Mock
    private VideoOrderService testService;

    @BeforeEach
    void setUp() {
        testService = new VideoOrderServiceImplementation(playlistService);
    }

    @Test
    void givenPlaylistWithVideos_whenChangeIndexOfVideo_thenPositionsChanged() {

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
        when(playlistService.getPlaylistById(playlist.getId())).thenReturn(playlist);
        testService.changeIndexOfVideoInPlaylist(playlist.getId(), v, 3);

        //then
        assertNotNull(playlist.getVideos());
        assertFalse(playlist.getVideos().isEmpty());
        assertEquals(3, playlist.getVideos().get(0).getPosition());
        assertEquals(1, playlist.getVideos().get(1).getPosition());
        assertEquals(2, playlist.getVideos().get(2).getPosition());
    }

}