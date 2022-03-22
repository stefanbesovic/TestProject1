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
	public VideoDto saveVideo(@RequestBody Video video) {
		videoService.saveVideo(video);
		return VideoMapper.INSTANCE.toDto(video);
	}
	
	@GetMapping()
	public List<VideoDto> getAllVideos() {
		return videoService.getAllVideos()
				.stream()
				.map(VideoMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public VideoDto getVideoById(@PathVariable("id") long id) {
		return VideoMapper.INSTANCE.toDto(videoService.getVideoById(id));
	}
	
	@PutMapping("{id}")
	public VideoDto updateVideo(@RequestBody VideoDto videoDto, @PathVariable("id") long id) {
		videoService.updateVideo(VideoMapper.INSTANCE.fromDto(videoDto), id);
		return videoDto;
	}
	
	@DeleteMapping("{id}")
	public void deleteVideo(@PathVariable("id") long id) {
		videoService.deleteVideo(id);
	}
	
	@PutMapping("/{videoId}/categories/{categoryId}")
	public VideoDto addCategory(@PathVariable("videoId") long videoId, @PathVariable("categoryId") long categoryId) {
		return VideoMapper.INSTANCE.toDto(videoService.addCategory(videoId, categoryId));
	}
	
	@DeleteMapping("/{videoId}/categories/{categoryId}")
	public VideoDto removeCategory(@PathVariable("videoId") long videoId, @PathVariable("categoryId") long categoryId) {
		videoService.RemoveCategory(videoId, categoryId);
		return VideoMapper.INSTANCE.toDto(videoService.getVideoById(videoId));
	}
}
