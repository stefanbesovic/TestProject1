package com.practice.test1.service;

import java.util.List;

import com.practice.test1.domen.Category;
import com.practice.test1.domen.Video;

public interface VideoService {
	Video saveVideo(Video video);
	Video getVideoById(long id);
	List<Video> getAllVideos();
	Video updateVideo(Video video, long id);
	void deleteVideo(long id);
	Video addCategory(long videoId, long categoryId);
	void RemoveCategory(long videoId, long categoryId);
}
