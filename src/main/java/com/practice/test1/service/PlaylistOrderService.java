package com.practice.test1.service;

import java.util.List;

import com.practice.test1.domen.Channel;
import com.practice.test1.domen.Playlist;

public interface PlaylistOrderService {
	List<Playlist> sortPlaylists(Channel channel);
	Channel addPlaylistToChannel(long channelId, Playlist playlist);
	void removePlaylistFromChannel(long channelId, Playlist playlist);
	void changeIndexOfPlaylistInChannel(long channelId, Playlist playlist, int newPosition);
}
