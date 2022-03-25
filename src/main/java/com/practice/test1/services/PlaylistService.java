package com.practice.test1.services;

import java.util.List;
import java.util.Set;

import com.practice.test1.entities.Playlist;

public interface PlaylistService {
	Playlist savePlaylist(Playlist playlist);
	Playlist getPlaylistById(Long id);
	List<Playlist> getAllPlaylists();
	Playlist updatePlaylist(Playlist playlist, Long id);
	void deletePlaylist(Long id);
	Playlist addCategory(Long playlistId, Long categoryId);
	void RemoveCategory(Long playlistId, Long categoryId);
	Set<Playlist> addPlaylistToUser(Long playlistId, Long userId);
	Playlist removePlaylistFromUser(Long playlistId);
}
