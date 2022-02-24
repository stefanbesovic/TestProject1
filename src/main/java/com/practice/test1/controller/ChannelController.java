package com.practice.test1.controller;

import java.util.List;

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
		this.channelService = channelService;
		this.playlistService = playlistService;
		this.playlistOrderService = playlistOrderService;
	}

	@PostMapping()
	public Channel saveChannel(@RequestBody Channel channel) {
		return channelService.saveChannel(channel);
	}
	
	@GetMapping()
	public List<Channel> getAllChannels() {
		return channelService.getAllChannels();
	}
	
	@GetMapping("{id}")
	public Channel getChannelById(@PathVariable("id") long id) {
		return channelService.getChannelById(id);
	}
	
	@PutMapping("{id}")
	public Channel updateChannel(@PathVariable("id") long id, @RequestBody Channel channel) {
		return channelService.updateChannel(channel, id);
	}
	
	@DeleteMapping("{id}")
	public void DeleteChannel(@PathVariable("id") long id) {
		channelService.deleteChannel(id);
	}
	
	@PutMapping("/{channelId}/playlists/{playlistId}")
	public Channel addPlaylistToChannel(@PathVariable("channelId") long channelId, @PathVariable("playlistId") long playlistId){
		return playlistOrderService.addPlaylistToChannel(channelId, playlistService.getPlaylistById(playlistId));
	}
	
	@DeleteMapping("/{channelId}/playlists/{playlistId}")
	public void removePlaylistFromChannel(@PathVariable("channelId") long channelId, @PathVariable("playlistId") long playlistId){
		playlistOrderService.removePlaylistFromChannel(channelId, playlistService.getPlaylistById(playlistId));
	}
	
	@GetMapping("/{channelId}/sort")
	public List<Playlist> sortPlaylistsInChannel(@PathVariable("channelId") long channelId) {
		return playlistOrderService.sortPlaylists(channelService.getChannelById(channelId));
	}
	
	@PutMapping("/{channelId}/playlists/{playlistId}/{newPosition}")
	public void changePositionOfVideoInPlaylist(@PathVariable("channelId") long channelId,
															@PathVariable("playlistId") long playlistId, 
															@PathVariable("newPosition") int newPosition) {
		playlistOrderService.changeIndexOfPlaylistInChannel(channelId, playlistService.getPlaylistById(playlistId), newPosition);
	}
}
