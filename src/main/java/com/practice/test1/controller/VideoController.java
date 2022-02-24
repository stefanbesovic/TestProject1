package com.practice.test1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.test1.domen.Video;
import com.practice.test1.service.VideoService;

@RestController
@RequestMapping("api/videos")
public class VideoController {

	private final VideoService videoService;

	public VideoController(VideoService videoService) {
		super();
		this.videoService = videoService;
	}
	
	@PostMapping()
	public ResponseEntity<Video> saveVideo(@RequestBody Video video) {
		return new ResponseEntity<Video>(videoService.saveVideo(video), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public List<Video> getAllVideos() {
		return videoService.getAllVideos();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Video> getVideoById(@PathVariable("id") long id) {
		return new ResponseEntity<Video>(videoService.getVideoById(id), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Video> updateVideo(@RequestBody Video video, @PathVariable("id") long id) {
		return new ResponseEntity<Video>(videoService.updateVideo(video, id), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteVideo(@PathVariable("id") long id) {
		videoService.deleteVideo(id);
		return new ResponseEntity<String>("Video deleted successfully!", HttpStatus.OK);
	}
	
	@PutMapping("/{videoId}/categories/{categoryId}")
	public ResponseEntity<Video> addCategory(@PathVariable("videoId") long videoId, @PathVariable("categoryId") long categoryId) {
		return new ResponseEntity<Video>(videoService.addCategory(videoId, categoryId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{videoId}/categories/{categoryId}")
	public ResponseEntity<String> removeCategory(@PathVariable("videoId") long videoId, @PathVariable("categoryId") long categoryId) {
		videoService.RemoveCategory(videoId, categoryId);
		return new ResponseEntity<String>("Category removed from video.", HttpStatus.OK);
	}
}
