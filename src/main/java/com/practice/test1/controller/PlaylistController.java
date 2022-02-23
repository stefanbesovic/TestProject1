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

import com.practice.test1.domen.Playlist;
import com.practice.test1.service.PlaylistService;
import com.practice.test1.service.VideoOrderService;
import com.practice.test1.service.VideoService;

@RestController
@RequestMapping("api/playlists")
public class PlaylistController {
	
	private PlaylistService playlistService;
	private VideoOrderService videoOrderService;
	private VideoService videoService;

	public PlaylistController(PlaylistService playlistService, VideoOrderService videoOrderService,
			VideoService videoService) {
		super();
		this.playlistService = playlistService;
		this.videoOrderService = videoOrderService;
		this.videoService = videoService;
	}
	
	@PostMapping()
	public ResponseEntity<Playlist> savePlaylist(@RequestBody Playlist playlist) {
		return new ResponseEntity<Playlist>(playlistService.savePlaylist(playlist), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public List<Playlist> getAllPlaylists() {
		return playlistService.getAllPlaylists();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Playlist> getPlaylistById(@PathVariable("id") long id) {
		return new ResponseEntity<Playlist>(playlistService.getPlaylistById(id), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Playlist> updatePlaylist(@RequestBody Playlist playlist, @PathVariable("id") long id) {
		return new ResponseEntity<Playlist>(playlistService.updatePlaylist(playlist, id), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePlaylist(@PathVariable("id") long id) {
		playlistService.deletePlaylist(id);
		return new ResponseEntity<String>("Playlist deleted successfully!", HttpStatus.OK);
	}
	
	@PutMapping("/{playlistId}/categories/{categoryId}")
	public ResponseEntity<Playlist> addCategory(@PathVariable("playlistId") long playlistId, @PathVariable("categoryId") long categoryId) {
		return new ResponseEntity<Playlist>(playlistService.addCategory(playlistId, categoryId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{playlistId}/categories/{categoryId}")
	public ResponseEntity<String> removeCategory(@PathVariable("playlistId") long playlistId, @PathVariable("categoryId") long categoryId) {
		playlistService.RemoveCategory(playlistId, categoryId);
		return new ResponseEntity<String>("Category removed from playlist.", HttpStatus.OK);
	}
	
	@PutMapping("/{playlistId}/videos/{videoId}")
	public ResponseEntity<Playlist> addVideoToPlaylist(@PathVariable("playlistId") long playlistId, @PathVariable("videoId") long videoId){
		return new ResponseEntity<Playlist>(videoOrderService.addVideoToPlaylist(playlistId, videoService.getVideoById(videoId)), HttpStatus.OK);
	}
	
	@DeleteMapping("/{playlistId}/videos/{videoId}")
	public ResponseEntity<String> RemoveVideoFromPlaylist(@PathVariable("playlistId") long playlistId, @PathVariable("videoId") long videoId){
		videoOrderService.RemoveVideoFromPlaylist(playlistId, videoService.getVideoById(videoId));
		return new ResponseEntity<String>("Video removed from playlist.", HttpStatus.OK);
	}
}
