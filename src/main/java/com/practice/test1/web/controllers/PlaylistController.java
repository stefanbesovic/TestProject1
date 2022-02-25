package com.practice.test1.web.controllers;

import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.test1.entities.Playlist;
import com.practice.test1.entities.Video;
import com.practice.test1.services.PlaylistService;
import com.practice.test1.services.VideoOrderService;
import com.practice.test1.services.VideoService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/playlists")
public class PlaylistController {
	
	private final PlaylistService playlistService;
	private final VideoOrderService videoOrderService;
	private final VideoService videoService;
	
	@PostMapping()
	public Playlist savePlaylist(@RequestBody Playlist playlist) {
		return playlistService.savePlaylist(playlist);
	}
	
	@GetMapping()
	public List<Playlist> getAllPlaylists() {
		return playlistService.getAllPlaylists();
	}
	
	@GetMapping("{id}")
	public Playlist getPlaylistById(@PathVariable("id") long id) {
		return playlistService.getPlaylistById(id);
	}
	
	@PutMapping("{id}")
	public Playlist updatePlaylist(@RequestBody Playlist playlist, @PathVariable("id") long id) {
		return playlistService.updatePlaylist(playlist, id);
	}
	
	@DeleteMapping("{id}")
	public void deletePlaylist(@PathVariable("id") long id) {
		playlistService.deletePlaylist(id);
	}
	
	@PutMapping("/{playlistId}/categories/{categoryId}")
	public Playlist addCategory(@PathVariable("playlistId") long playlistId, @PathVariable("categoryId") long categoryId) {
		return playlistService.addCategory(playlistId, categoryId);
	}
	
	@DeleteMapping("/{playlistId}/categories/{categoryId}")
	public void removeCategory(@PathVariable("playlistId") long playlistId, @PathVariable("categoryId") long categoryId) {
		playlistService.RemoveCategory(playlistId, categoryId);
	}
	
	@PutMapping("/{playlistId}/videos/{videoId}")
	public Playlist addVideoToPlaylist(@PathVariable("playlistId") long playlistId, @PathVariable("videoId") long videoId){
		return videoOrderService.addVideoToPlaylist(playlistId, videoService.getVideoById(videoId));
	}
	
	@DeleteMapping("/{playlistId}/videos/{videoId}")
	public void removeVideoFromPlaylist(@PathVariable("playlistId") long playlistId, @PathVariable("videoId") long videoId){
		videoOrderService.removeVideoFromPlaylist(playlistId, videoService.getVideoById(videoId));
	}
	
	@GetMapping("/{playlistId}/sort")
	public List<Video> sortVideosInPlaylist(@PathVariable("playlistId") long playlistId) {
		return videoOrderService.sortVideos(playlistService.getPlaylistById(playlistId));
	}
	
	@PutMapping("/{playlistId}/videos/{videoId}/{newPosition}")
	public void changePositionOfVideoInPlaylist(@PathVariable("playlistId") long playlistId,
															@PathVariable("videoId") long videoId, 
															@PathVariable("newPosition") int newPosition) {
		videoOrderService.changeIndexOfVideoInPlaylist(playlistId, videoService.getVideoById(videoId), newPosition);
	}
	
	@PutMapping("/{playlistId}/user/{userId}")
	public Set<Playlist> addPlaylistToUser(@PathVariable("playlistId") long playlistId, @PathVariable("userId") long userId){
		return playlistService.addPlaylistToUser(playlistId, userId);
	}
	
	@DeleteMapping("/{playlistId}/user")
	public void removePlaylistFromUser(@PathVariable("playlistId") long playlistId){
		playlistService.removePlaylistFromUser(playlistId);
	}
}
