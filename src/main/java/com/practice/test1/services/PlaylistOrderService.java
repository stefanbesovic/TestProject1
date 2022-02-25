package com.practice.test1.services;

import java.util.List;

import com.practice.test1.entities.Channel;
import com.practice.test1.entities.Playlist;

public interface PlaylistOrderService {
	List<Playlist> sortPlaylists(Channel channel);
	Channel addPlaylistToChannel(long channelId, Playlist playlist);
	void removePlaylistFromChannel(long channelId, Playlist playlist);
	void changeIndexOfPlaylistInChannel(long channelId, Playlist playlist, int newPosition);
}
