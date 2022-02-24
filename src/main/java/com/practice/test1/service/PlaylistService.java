package com.practice.test1.service;

import java.util.List;
import java.util.Set;

import com.practice.test1.domen.Playlist;

public interface PlaylistService {
	Playlist savePlaylist(Playlist playlist);
	Playlist getPlaylistById(long id);
	List<Playlist> getAllPlaylists();
	Playlist updatePlaylist(Playlist playlist, long id);
	void deletePlaylist(long id);
	Playlist addCategory(long playlistId, long categoryId);
	void RemoveCategory(long playlistId, long categoryId);
	Set<Playlist> addPlaylistToUser(long playlistId, long userId);
	Playlist removePlaylistFromUser(long playlistId);
}
