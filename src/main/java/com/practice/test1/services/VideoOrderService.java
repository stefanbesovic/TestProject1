package com.practice.test1.services;

import java.util.List;

import com.practice.test1.entities.Playlist;
import com.practice.test1.entities.Video;

public interface VideoOrderService {
	List<Video> sortVideos(Playlist playlist);
	Playlist addVideoToPlaylist(long playlistId, Video video);
	void removeVideoFromPlaylist(long playlistId, Video video);
	void changeIndexOfVideoInPlaylist(long playlistId, Video video, int newPosition);
}
