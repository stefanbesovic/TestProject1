package com.practice.test1.service;

import com.practice.test1.domen.Playlist;
import com.practice.test1.domen.Video;

public interface VideoOrderService {

	void sortVideos(Playlist playlist);
	Playlist addVideoToPlaylist(long playlistId, Video video);
	void RemoveVideoFromPlaylist(long playlistId, Video video);
	void ChangeIndexOfVideoInPlaylist(long playlistId, Video video, long newPlace);
}
