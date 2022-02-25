package com.practice.test1.services.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.practice.test1.entities.Category;
import com.practice.test1.entities.Video;
import com.practice.test1.repositories.VideoRepository;
import com.practice.test1.services.CategoryService;
import com.practice.test1.services.VideoService;

@RequiredArgsConstructor
@Service
public class VideoServiceImplementation implements VideoService {

	private final VideoRepository videoRepository;
	private final CategoryService categoryService;
	
	@Override
	public Video saveVideo(Video video) {
		if(!videoRepository.existsById(video.getId())) {
			return videoRepository.save(video);
		}else {
			throw new DuplicateKeyException("Could not save video. Video with same ID already exists.");
		}
	}

	@Override
	public Video getVideoById(long id) {
		return videoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("Video not found: %d", id)));
	}

	@Override
	public List<Video> getAllVideos() {
		return videoRepository.findAll();
	}

	@Override
	public Video updateVideo(Video video, long id) {
		Video existing = getVideoById(id);
		existing.setName(video.getName());
		videoRepository.save(existing);
		return existing;
	}

	@Override
	public void deleteVideo(long id) {
		getVideoById(id);
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
		}
	}
}
