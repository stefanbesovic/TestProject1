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

import com.practice.test1.domen.Channel;
import com.practice.test1.domen.Playlist;
import com.practice.test1.service.ChannelService;
import com.practice.test1.service.PlaylistOrderService;
import com.practice.test1.service.PlaylistService;

@RestController
@RequestMapping("api/channels")
public class ChannelController {
	
	private final ChannelService channelService;
	private final PlaylistService playlistService;
	private final PlaylistOrderService playlistOrderService;
	
	public ChannelController(ChannelService channelService, PlaylistService playlistService, PlaylistOrderService playlistOrderService) {
		super();
		this.channelService = channelService;
		this.playlistService = playlistService;
		this.playlistOrderService = playlistOrderService;
	}

	@PostMapping()
	public ResponseEntity<Channel> saveChannel(@RequestBody Channel channel) {
		return new ResponseEntity<Channel>(channelService.saveChannel(channel), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public List<Channel> getAllChannels() {
		return channelService.getAllChannels();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Channel> getChannelById(@PathVariable("id") long id) {
		return new ResponseEntity<Channel>(channelService.getChannelById(id), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Channel> updateChannel(@PathVariable("id") long id, @RequestBody Channel channel) {
		return new ResponseEntity<Channel>(channelService.updateChannel(channel, id), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> DeleteChannel(@PathVariable("id") long id) {
		channelService.deleteChannel(id);
		return new ResponseEntity<String>("Channel deleted successfully!", HttpStatus.OK);
	}
	
	@PutMapping("/{channelId}/playlists/{playlistId}")
	public ResponseEntity<Channel> addPlaylistToChannel(@PathVariable("channelId") long channelId, @PathVariable("playlistId") long playlistId){
		return new ResponseEntity<Channel>(playlistOrderService.addPlaylistToChannel(channelId, playlistService.getPlaylistById(playlistId)), HttpStatus.OK);
	}
	
	@DeleteMapping("/{channelId}/playlists/{playlistId}")
	public ResponseEntity<String> removePlaylistFromChannel(@PathVariable("channelId") long channelId, @PathVariable("playlistId") long playlistId){
		playlistOrderService.removePlaylistFromChannel(channelId, playlistService.getPlaylistById(playlistId));
		return new ResponseEntity<String>("Playlist removed from channel.", HttpStatus.OK);
	}
	
	@GetMapping("/{channelId}/sort")
	public List<Playlist> sortPlaylistsInChannel(@PathVariable("channelId") long channelId) {
		return playlistOrderService.sortPlaylists(channelService.getChannelById(channelId));
	}
	
	@PutMapping("/{channelId}/playlists/{playlistId}/{newPosition}")
	public ResponseEntity<String> changePositionOfVideoInPlaylist(@PathVariable("channelId") long channelId,
															@PathVariable("playlistId") long playlistId, 
															@PathVariable("newPosition") int newPosition) {
		playlistOrderService.changeIndexOfPlaylistInChannel(channelId, playlistService.getPlaylistById(playlistId), newPosition);
		return new ResponseEntity<String>("Index changed successfully.", HttpStatus.OK);
	}
}
