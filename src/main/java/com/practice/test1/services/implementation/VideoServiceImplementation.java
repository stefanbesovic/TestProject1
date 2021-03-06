package com.practice.test1.services.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.practice.test1.entities.Category;
import com.practice.test1.entities.Video;
import com.practice.test1.repositories.VideoRepository;
import com.practice.test1.services.CategoryService;
import com.practice.test1.services.VideoService;

@RequiredArgsConstructor
@Service
@Slf4j
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
	public Video getVideoById(Long id) {
		return videoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("Video not found: %d", id)));
	}

	@Override
	public List<Video> getAllVideos() {
		return videoRepository.findAll();
	}

	@Override
	public Video updateVideo(Video video, Long id) {
		Video existing = getVideoById(id);
		existing.setName(video.getName());

		videoRepository.save(existing);

		return existing;
	}

	@Override
	public void deleteVideo(Long id) {
		Video videoById = getVideoById(id);
		videoRepository.deleteById(videoById.getId());
	}
	
	@Override
	public Video addCategory(Long videoId, Long categoryId) {
		log.debug("Adding category {} to video {}.", categoryId, videoId);

		Video video = getVideoById(videoId);
		Category category = categoryService.getCategoryById(categoryId);

		if(!video.getCategories().contains(category)) {
			video.addCategory(category);
		}else {
			log.debug("Can't add category {} because it doesn't exist.", categoryId);
			return video;
		}

		log.info("Category {} added to video {}.", categoryId, videoId);

		return videoRepository.save(video);
	}

	@Override
	public void RemoveCategory(Long videoId, Long categoryId) {
		log.debug("Removing category {} from video {}.", categoryId, videoId);

		Video video = getVideoById(videoId);
		Category category = categoryService.getCategoryById(categoryId);

		if(video.getCategories().contains(category)) {
			video.removeCategory(category);
			log.info("Category {} removed from video {}.", categoryId, videoId);
			videoRepository.save(video);
		}
	}
}
