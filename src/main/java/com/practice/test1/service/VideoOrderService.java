package com.practice.test1.service;

import java.util.List;

import com.practice.test1.domen.Playlist;
import com.practice.test1.domen.Video;

public interface VideoOrderService {
	List<Video> sortVideos(Playlist playlist);
	Playlist addVideoToPlaylist(long playlistId, Video video);
	void removeVideoFromPlaylist(long playlistId, Video video);
	void changeIndexOfVideoInPlaylist(long playlistId, Video video, int newPosition);
}
