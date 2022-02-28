package com.practice.test1.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.test1.web.dto.video.VideoDto;
import com.practice.test1.web.dto.video.VideoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.test1.entities.Video;
import com.practice.test1.services.VideoService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/videos")
public class VideoController {

	private final VideoService videoService;

	@PostMapping()
	public Video saveVideo(@RequestBody VideoDto videoDto) {
		return videoService.saveVideo(VideoMapper.INSTANCE.fromDto(videoDto));
	}
	
	@GetMapping()
	public List<VideoDto> getAllVideos() {
		return videoService.getAllVideos()
				.stream()
				.map(video -> VideoMapper.INSTANCE.toDto(video))
				.collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public VideoDto getVideoById(@PathVariable("id") long id) {
		return VideoMapper.INSTANCE.toDto(videoService.getVideoById(id));
	}
	
	@PutMapping("{id}")
	public Video updateVideo(@RequestBody VideoDto videoDto, @PathVariable("id") long id) {
		return videoService.updateVideo(VideoMapper.INSTANCE.fromDto(videoDto), id);
	}
	
	@DeleteMapping("{id}")
	public void deleteVideo(@PathVariable("id") long id) {
		videoService.deleteVideo(id);
	}
	
	@PutMapping("/{videoId}/categories/{categoryId}")
	public Video addCategory(@PathVariable("videoId") long videoId, @PathVariable("categoryId") long categoryId) {
		return videoService.addCategory(videoId, categoryId);
	}
	
	@DeleteMapping("/{videoId}/categories/{categoryId}")
	public void removeCategory(@PathVariable("videoId") long videoId, @PathVariable("categoryId") long categoryId) {
		videoService.RemoveCategory(videoId, categoryId);
	}
}
