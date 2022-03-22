package com.practice.test1.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.test1.web.dto.channel.ChannelDto;
import com.practice.test1.web.dto.channel.ChannelMapper;
import com.practice.test1.web.dto.playlist.PlaylistDto;
import com.practice.test1.web.dto.playlist.PlaylistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.test1.entities.Channel;
import com.practice.test1.entities.Playlist;
import com.practice.test1.services.ChannelService;
import com.practice.test1.services.ChannelPlaylistService;
import com.practice.test1.services.PlaylistService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/channels")
public class ChannelController {
	
	private final ChannelService channelService;
	private final PlaylistService playlistService;
	private final ChannelPlaylistService channelPlaylistService;

	@PostMapping()
	public ChannelDto saveChannel(@RequestBody ChannelDto channelDto) {
		channelService.saveChannel(ChannelMapper.INSTANCE.fromDto(channelDto));
		return channelDto;
	}
	
	@GetMapping()
	public List<ChannelDto> getAllChannels() {
		return channelService.getAllChannels()
				.stream().map(ChannelMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public ChannelDto getChannelById(@PathVariable("id") long id) {
		return ChannelMapper.INSTANCE.toDto(channelService.getChannelById(id));
	}
	
	@PutMapping("{id}")
	public ChannelDto updateChannel(@PathVariable("id") long id, @RequestBody ChannelDto channelDto) {
		channelService.updateChannel(ChannelMapper.INSTANCE.fromDto(channelDto), id);
		return channelDto;
	}
	
	@DeleteMapping("{id}")
	public void DeleteChannel(@PathVariable("id") long id) {
		channelService.deleteChannel(id);
	}
	
	@PutMapping("/{channelId}/playlists/{playlistId}")
	public ChannelDto addPlaylistToChannel(@PathVariable("channelId") long channelId, @PathVariable("playlistId") long playlistId){
		return ChannelMapper.INSTANCE.toDto(channelPlaylistService.addPlaylistToChannel(channelId, playlistService.getPlaylistById(playlistId)));
	}
	
	@DeleteMapping("/{channelId}/playlists/{playlistId}")
	public void removePlaylistFromChannel(@PathVariable("channelId") long channelId, @PathVariable("playlistId") long playlistId){
		channelPlaylistService.removePlaylistFromChannel(channelId, playlistService.getPlaylistById(playlistId));
	}
	
	@GetMapping("/{channelId}/sort")
	public List<PlaylistDto> sortPlaylistsInChannel(@PathVariable("channelId") long channelId) {
		return channelPlaylistService.sortPlaylists(channelService.getChannelById(channelId))
				.stream()
				.map(PlaylistMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
	
	@PutMapping("/{channelId}/playlists/{playlistId}/{newPosition}")
	public void changePositionOfVideoInPlaylist(@PathVariable("channelId") long channelId,
															@PathVariable("playlistId") long playlistId, 
															@PathVariable("newPosition") int newPosition) {
		channelPlaylistService.changeIndexOfPlaylistInChannel(channelId, playlistService.getPlaylistById(playlistId), newPosition);
	}
}
