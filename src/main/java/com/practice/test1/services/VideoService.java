package com.practice.test1.services;

import java.util.List;

import com.practice.test1.entities.Video;

public interface VideoService {
	Video saveVideo(Video video);
	Video getVideoById(Long id);
	List<Video> getAllVideos();
	Video updateVideo(Video video, Long id);
	void deleteVideo(Long id);
	Video addCategory(Long videoId, Long categoryId);
	void RemoveCategory(Long videoId, Long categoryId);
}
