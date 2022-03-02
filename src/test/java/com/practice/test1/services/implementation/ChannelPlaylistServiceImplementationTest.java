package com.practice.test1.services.implementation;

import com.practice.test1.entities.Channel;
import com.practice.test1.entities.Playlist;
import com.practice.test1.entities.ChannelPlaylist;
import com.practice.test1.services.ChannelPlaylistService;
import com.practice.test1.services.ChannelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChannelPlaylistServiceImplementationTest {
    private ChannelService channelService;
    private ChannelPlaylistService testService;

    @BeforeEach
    void setUp() {
        channelService = mock(ChannelService.class);
        testService = new ChannelPlaylistServiceImplementation(channelService);
    }

    @Test
    void givenChannelWithPlaylists_whenChangeIndexOfPlaylistFromLeftToRight_thenPositionsChanges() {

        //given
        Channel channel = new Channel();
        channel.setName("Channel1");
        Playlist v = new Playlist();
        v.setName("Playlist");
        Playlist v1 = new Playlist();
        v1.setName("Playlist1");
        Playlist v2 = new Playlist();
        v2.setName("Playlist2");
        channel.getPlaylists().add(new ChannelPlaylist(channel, v, 1));
        channel.getPlaylists().add(new ChannelPlaylist(channel, v1, 2));
        channel.getPlaylists().add(new ChannelPlaylist(channel, v2, 3));

        //when
        when(channelService.getChannelById(anyLong())).thenReturn(channel);
        testService.changeIndexOfPlaylistInChannel(channel.getId(), v, 3);

        //then
        assertNotNull(channel.getPlaylists());
        assertFalse(channel.getPlaylists().isEmpty());
        assertEquals(3, channel.getPlaylists().get(0).getPosition());
        assertEquals(1, channel.getPlaylists().get(1).getPosition());
        assertEquals(2, channel.getPlaylists().get(2).getPosition());
    }

    @Test
    void givenChannelWithPlaylists_whenChangeIndexOfPlaylistFromRightToLeft_thenPositionsChanges() {

        //given
        Channel channel = new Channel();
        channel.setName("Channel1");
        Playlist v = new Playlist();
        v.setName("Playlist");
        Playlist v1 = new Playlist();
        v1.setName("Playlist1");
        Playlist v2 = new Playlist();
        v2.setName("Playlist2");
        channel.getPlaylists().add(new ChannelPlaylist(channel, v, 1));
        channel.getPlaylists().add(new ChannelPlaylist(channel, v1, 2));
        channel.getPlaylists().add(new ChannelPlaylist(channel, v2, 3));

        //when
        when(channelService.getChannelById(anyLong())).thenReturn(channel);
        testService.changeIndexOfPlaylistInChannel(channel.getId(), v2, 1);

        //then
        assertNotNull(channel.getPlaylists());
        assertFalse(channel.getPlaylists().isEmpty());
        assertEquals(2, channel.getPlaylists().get(0).getPosition());
        assertEquals(3, channel.getPlaylists().get(1).getPosition());
        assertEquals(1, channel.getPlaylists().get(2).getPosition());
    }

    @Test
    void givenChannelWithPlaylists_whenChangeIndexOfPlaylistWhichIsNotInChannel_thenThrowException() {

        //given
        Channel channel = new Channel();
        channel.setName("Channel1");
        Playlist v = new Playlist();
        v.setName("Playlist");
        Playlist v1 = new Playlist();
        v1.setName("Playlist1");
        Playlist v2 = new Playlist();
        v2.setName("Playlist2");
        channel.getPlaylists().add(new ChannelPlaylist(channel, v, 1));
        channel.getPlaylists().add(new ChannelPlaylist(channel, v1, 2));
        channel.getPlaylists().add(new ChannelPlaylist(channel, v2, 3));
        Playlist v3 = new Playlist();
        v3.setName("Playlist3");

        //when
        when(channelService.getChannelById(anyLong())).thenReturn(channel);
        Exception e = assertThrows(NoSuchElementException.class, () -> {
            testService.changeIndexOfPlaylistInChannel(channel.getId(), v3, 1);
        });

        //then
        assertNotNull(channel.getPlaylists());
        assertFalse(channel.getPlaylists().isEmpty());
        assertTrue(e.getMessage().contains(String.format("Can't change index of playlist in channel. Playlist not found: %d", v3.getId())));
    }


    @Test
    void givenChannelWithPlaylists_whenChangeIndexOfPlaylistPositionOutOfBound_thenThrowException() {

        //given
        Channel channel = new Channel();
        channel.setName("Channel1");
        Playlist v = new Playlist();
        v.setName("Playlist");
        Playlist v1 = new Playlist();
        v1.setName("Playlist1");
        Playlist v2 = new Playlist();
        v2.setName("Playlist2");
        channel.getPlaylists().add(new ChannelPlaylist(channel, v, 1));
        channel.getPlaylists().add(new ChannelPlaylist(channel, v1, 2));
        channel.getPlaylists().add(new ChannelPlaylist(channel, v2, 3));

        //when
        when(channelService.getChannelById(anyLong())).thenReturn(channel);
        Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
            testService.changeIndexOfPlaylistInChannel(channel.getId(), v1, 4);
        });

        //then
        assertNotNull(channel.getPlaylists());
        assertFalse(channel.getPlaylists().isEmpty());
        assertTrue(e.getMessage().contains(String.format("Position out of bounds. Position %d - Actual channel size %d", 4, channel.getPlaylists().size())));
    }

    @Test
    void givenPlaylistWithPlaylists_whenChangeIndexOfPlaylistToSamePosition_thenPositionsDoesNotChanges() {

        //given
        Channel channel = new Channel();
        channel.setName("Channel1");
        Playlist v = new Playlist();
        v.setName("Playlist");
        Playlist v1 = new Playlist();
        v1.setName("Playlist1");
        Playlist v2 = new Playlist();
        v2.setName("Playlist2");
        channel.getPlaylists().add(new ChannelPlaylist(channel, v, 1));
        channel.getPlaylists().add(new ChannelPlaylist(channel, v1, 2));
        channel.getPlaylists().add(new ChannelPlaylist(channel, v2, 3));

        //when
        when(channelService.getChannelById(anyLong())).thenReturn(channel);
        testService.changeIndexOfPlaylistInChannel(channel.getId(), v, 1);

        //then
        assertNotNull(channel.getPlaylists());
        assertFalse(channel.getPlaylists().isEmpty());
        assertEquals(1, channel.getPlaylists().get(0).getPosition());
        assertEquals(2, channel.getPlaylists().get(1).getPosition());
        assertEquals(3, channel.getPlaylists().get(2).getPosition());
    }

    @Test
    void givenPlaylistWithPlaylists_whenSortPlaylistPlaylistsByPosition_thenReturnSorted() {

        //given
        Channel channel = new Channel();
        channel.setName("Channel1");
        Playlist v = new Playlist();
        v.setName("Playlist");
        Playlist v1 = new Playlist();
        v1.setName("Playlist1");
        Playlist v2 = new Playlist();
        v2.setName("Playlist2");
        channel.getPlaylists().add(new ChannelPlaylist(channel, v, 3));
        channel.getPlaylists().add(new ChannelPlaylist(channel, v1, 1));
        channel.getPlaylists().add(new ChannelPlaylist(channel, v2, 2));

        //when
        testService.sortPlaylists(channel);

        //then
        assertNotNull(channel.getPlaylists());
        assertFalse(channel.getPlaylists().isEmpty());
        assertEquals(1, channel.getPlaylists().get(0).getPosition());
        assertEquals(2, channel.getPlaylists().get(1).getPosition());
        assertEquals(3, channel.getPlaylists().get(2).getPosition());
    }

    @Test
    void givenPlaylistAndPlaylist_whenAddPlaylistToPlaylist_thenPlaylistWithAddedPlaylist() {

        //given
        Channel channel = new Channel();
        channel.setName("Channel1");
        Playlist v = new Playlist();
        v.setName("Playlist");

        //when
        when(channelService.getChannelById(anyLong())).thenReturn(channel);
        testService.addPlaylistToChannel(channel.getId(), v);

        //then
        assertNotNull(channel.getPlaylists());
        assertFalse(channel.getPlaylists().isEmpty());
        assertEquals(v, channel.getPlaylists().get(0).getPlaylist());
    }

    @Test
    void givenPlaylistAndPlaylist_whenRemovePlaylistFromPlaylistWherePlaylistIsInPlaylist_thenReturnPlaylistWithoutPlaylist() {

        //given
        Channel channel = new Channel();
        channel.setName("Channel1");
        Playlist v = new Playlist();
        v.setName("Playlist");
        channel.getPlaylists().add(new ChannelPlaylist(channel, v, 1));

        //when
        when(channelService.getChannelById(anyLong())).thenReturn(channel);
        testService.removePlaylistFromChannel(channel.getId(), v);

        //then
        assertNotNull(channel.getPlaylists());
        assertTrue(channel.getPlaylists().isEmpty());
    }

    @Test
    void givenPlaylistAndPlaylist_whenRemovePlaylistFromPlaylistWherePlaylistIsNotInPlaylist_thenReturnIntactPlaylist() {
//given
        Channel channel = new Channel();
        channel.setName("Channel1");
        Playlist v = new Playlist();
        v.setName("Playlist");
        channel.getPlaylists().add(new ChannelPlaylist(channel, v, 1));
        Playlist v1 = new Playlist();
        v1.setName("Playlist1");

        //when
        when(channelService.getChannelById(anyLong())).thenReturn(channel);
        testService.removePlaylistFromChannel(channel.getId(), v1);

        //then
        assertNotNull(channel.getPlaylists());
        assertFalse(channel.getPlaylists().isEmpty());
        assertEquals(v, channel.getPlaylists().get(0).getPlaylist());
    }
}