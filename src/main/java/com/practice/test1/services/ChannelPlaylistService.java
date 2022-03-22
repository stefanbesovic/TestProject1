package com.practice.test1.services;

import java.util.List;

import com.practice.test1.entities.Channel;
import com.practice.test1.entities.ChannelPlaylist;
import com.practice.test1.entities.Playlist;

public interface ChannelPlaylistService {
	List<ChannelPlaylist> sortPlaylists(Channel channel);
	ChannelPlaylist addPlaylistToChannel(long channelId, Playlist playlist);
	List<ChannelPlaylist> removePlaylistFromChannel(long channelId, Playlist playlist);
	List<ChannelPlaylist> changeIndexOfPlaylistInChannel(long channelId, Playlist playlist, int newPosition);
}
