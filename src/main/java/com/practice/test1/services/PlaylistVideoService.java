package com.practice.test1.services;

import java.util.List;

import com.practice.test1.entities.Playlist;
import com.practice.test1.entities.PlaylistVideo;
import com.practice.test1.entities.Video;

public interface PlaylistVideoService {
	List<PlaylistVideo> sortVideos(Playlist playlist);
	PlaylistVideo addVideoToPlaylist(Long playlistId, Video video);
	List<PlaylistVideo> removeVideoFromPlaylist(Long playlistId, Video video);
	List<PlaylistVideo> changeIndexOfVideoInPlaylist(Long playlistId, Video video, int newPosition);
}
