package com.practice.test1.web.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.practice.test1.services.PlaylistVideoService;
import com.practice.test1.web.dto.playlist.PlaylistDto;
import com.practice.test1.web.dto.playlist.PlaylistMapper;
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

import com.practice.test1.entities.Playlist;
import com.practice.test1.entities.Video;
import com.practice.test1.services.PlaylistService;
import com.practice.test1.services.VideoService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/playlists")
public class PlaylistController {
	
	private final PlaylistService playlistService;
	private final PlaylistVideoService playlistVideoService;
	private final VideoService videoService;
	
	@PostMapping()
	public PlaylistDto savePlaylist(@RequestBody Playlist playlist) {
		playlistService.savePlaylist(playlist);
		return PlaylistMapper.INSTANCE.toDto(playlist);
	}
	
	@GetMapping()
	public List<PlaylistDto> getAllPlaylists() {
		return playlistService.getAllPlaylists()
				.stream()
				.map(PlaylistMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public PlaylistDto getPlaylistById(@PathVariable("id") long id) {
		return PlaylistMapper.INSTANCE.toDto(playlistService.getPlaylistById(id));
	}
	
	@PutMapping("{id}")
	public PlaylistDto updatePlaylist(@RequestBody PlaylistDto playlistDto, @PathVariable("id") long id) {
		playlistService.updatePlaylist(PlaylistMapper.INSTANCE.fromDto(playlistDto), id);
		return playlistDto;
	}
	
	@DeleteMapping("{id}")
	public void deletePlaylist(@PathVariable("id") long id) {
		playlistService.deletePlaylist(id);
	}
	
	@PutMapping("/{playlistId}/categories/{categoryId}")
	public PlaylistDto addCategory(@PathVariable("playlistId") long playlistId, @PathVariable("categoryId") long categoryId) {
		return PlaylistMapper.INSTANCE.toDto(playlistService.addCategory(playlistId, categoryId));
	}
	
	@DeleteMapping("/{playlistId}/categories/{categoryId}")
	public void removeCategory(@PathVariable("playlistId") long playlistId, @PathVariable("categoryId") long categoryId) {
		playlistService.RemoveCategory(playlistId, categoryId);
	}
	
	@PutMapping("/{playlistId}/videos/{videoId}")
	public PlaylistDto addVideoToPlaylist(@PathVariable("playlistId") long playlistId, @PathVariable("videoId") long videoId){
		return PlaylistMapper.INSTANCE.toDto(playlistVideoService.addVideoToPlaylist(playlistId, videoService.getVideoById(videoId)));
	}
	
	@DeleteMapping("/{playlistId}/videos/{videoId}")
	public void removeVideoFromPlaylist(@PathVariable("playlistId") long playlistId, @PathVariable("videoId") long videoId){
		playlistVideoService.removeVideoFromPlaylist(playlistId, videoService.getVideoById(videoId));
	}
	
	@GetMapping("/{playlistId}/sort")
	public List<VideoDto> sortVideosInPlaylist(@PathVariable("playlistId") long playlistId) {
		return playlistVideoService.sortVideos(playlistService.getPlaylistById(playlistId))
				.stream()
				.map(VideoMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
	
	@PutMapping("/{playlistId}/videos/{videoId}/{newPosition}")
	public void changePositionOfVideoInPlaylist(@PathVariable("playlistId") long playlistId,
															@PathVariable("videoId") long videoId, 
															@PathVariable("newPosition") int newPosition) {
		playlistVideoService.changeIndexOfVideoInPlaylist(playlistId, videoService.getVideoById(videoId), newPosition);
	}
	
	@PutMapping("/{playlistId}/user/{userId}")
	public Set<PlaylistDto> addPlaylistToUser(@PathVariable("playlistId") long playlistId, @PathVariable("userId") long userId){
		return playlistService.addPlaylistToUser(playlistId, userId)
				.stream()
				.map(PlaylistMapper.INSTANCE::toDto)
				.collect(Collectors.toSet());
	}
	
	@DeleteMapping("/{playlistId}/user")
	public void removePlaylistFromUser(@PathVariable("playlistId") long playlistId){
		playlistService.removePlaylistFromUser(playlistId);
	}
}
