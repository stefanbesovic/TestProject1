package com.practice.test1.services;

import java.util.List;

import com.practice.test1.entities.Playlist;
import com.practice.test1.entities.PlaylistVideo;
import com.practice.test1.entities.Video;

public interface PlaylistVideoService {
	List<PlaylistVideo> sortVideos(Playlist playlist);
	PlaylistVideo addVideoToPlaylist(long playlistId, Video video);
	List<PlaylistVideo> removeVideoFromPlaylist(long playlistId, Video video);
	List<PlaylistVideo> changeIndexOfVideoInPlaylist(long playlistId, Video video, int newPosition);
}
