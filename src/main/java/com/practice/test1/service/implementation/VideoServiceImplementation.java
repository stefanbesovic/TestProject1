package com.practice.test1.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.practice.test1.domen.Category;
import com.practice.test1.domen.Video;
import com.practice.test1.repository.CategoryRepository;
import com.practice.test1.repository.VideoRepository;
import com.practice.test1.service.CategoryService;
import com.practice.test1.service.VideoService;

@Service
public class VideoServiceImplementation implements VideoService {

	private final VideoRepository videoRepository;
	private final CategoryService categoryService;
	
	public VideoServiceImplementation(VideoRepository videoRepository, CategoryService categoryService) {
		super();
		this.videoRepository = videoRepository;
		this.categoryService = categoryService;
	}
	
	@Override
	public Video saveVideo(Video video) {
		if(!videoRepository.existsById(video.getId())) {
			return videoRepository.save(video);
		}else {
			throw new DuplicateKeyException(String.format("Could not save video. Video with same ID already exists."));
		}
	}

	@Override
	public Video getVideoById(long id) {
		return videoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not get. Video not found: %d", id)));
	}

	@Override
	public List<Video> getAllVideos() {
		return videoRepository.findAll();
	}

	@Override
	public Video updateVideo(Video video, long id) {
		Video existing = videoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not update. Video not found: %d", id)));
		existing.setName(video.getName());
		videoRepository.save(existing);
		return existing;
	}

	@Override
	public void deleteVideo(long id) {
		videoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not delete. Video not found: %d", id)));
		videoRepository.deleteById(id);
	}
	
	@Override
	public Video addCategory(long videoId, long categoryId) {
		Video video = getVideoById(videoId);
		Category category = categoryService.getCategoryById(categoryId);
		if(!video.getCategories().contains(category)) {
			video.addCategory(category);
		}else {
			return video;
		}
		return videoRepository.save(video);
	}

	@Override
	public void RemoveCategory(long videoId, long categoryId) {
		Video video = getVideoById(videoId);
		Category category = categoryService.getCategoryById(categoryId);
		if(video.getCategories().contains(category)) {
			video.removeCategory(category);
			videoRepository.save(video);
		}else {
			return;
		}
	}
}
