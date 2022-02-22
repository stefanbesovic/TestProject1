package com.practice.test1.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.test1.domen.Category;
import com.practice.test1.domen.Video;
import com.practice.test1.repository.CategoryRepository;
import com.practice.test1.repository.VideoRepository;
import com.practice.test1.service.VideoService;

@Service
public class VideoServiceImplementation implements VideoService {

	private VideoRepository videoRepository;
	private CategoryRepository categoryRepository;
	
	public VideoServiceImplementation(VideoRepository videoRepository, CategoryRepository categoryRepository) {
		super();
		this.videoRepository = videoRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public Video saveVideo(Video video) {
		if(!videoRepository.existsById(video.getId())) {
			return videoRepository.save(video);
		}else {
			return null;
		}
	}

	@Override
	public Video getVideoById(long id) {
		return videoRepository.findById(id).orElse(null);
	}

	@Override
	public List<Video> getAllVideos() {
		return videoRepository.findAll();
	}

	@Override
	public Video updateVideo(Video video, long id) {
		Video existing = videoRepository.findById(id).orElse(null);
		if(existing.equals(null)) {
			return null;
		}
		existing.setName(video.getName());
		videoRepository.save(existing);
		return existing;
	}

	@Override
	public void deleteVideo(long id) {
		Video exists = videoRepository.findById(id).orElse(null);
		if(exists.equals(null)) {
			return;
		}
		videoRepository.deleteById(id);
	}
	
	@Override
	public Video addCategory(long videoId, long categoryId) {
		Video video = videoRepository.findById(videoId).get();
		Category category = categoryRepository.findById(categoryId).get();
		if(video.equals(null)) {
			return null;
		}
		if(category.equals(null)) {
			return null;
		}
		if(!video.getCategories().contains(category)) {
			video.addCategory(category);
		}else {
			return video;
		}
		return videoRepository.save(video);
	}

	@Override
	public void RemoveCategory(long videoId, long categoryId) {
		Video video = videoRepository.findById(videoId).get();
		Category category = categoryRepository.findById(categoryId).get();
		if(video.equals(null)) {
			return;
		}
		if(category.equals(null)) {
			return;
		}
		if(video.getCategories().contains(category)) {
			video.removeCategory(category);
			videoRepository.save(video);	
		}else {
			return;
		}	
	}
}
