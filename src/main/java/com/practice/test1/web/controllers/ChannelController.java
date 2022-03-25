package com.practice.test1.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.test1.entities.Channel;
import com.practice.test1.web.dto.channel.ChannelDto;
import com.practice.test1.web.dto.channel.ChannelMapper;
import com.practice.test1.web.dto.channelplaylist.ChannelPlaylistGetDto;
import com.practice.test1.web.dto.channelplaylist.ChannelPlaylistGetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.test1.services.ChannelService;
import com.practice.test1.services.ChannelPlaylistService;
import com.practice.test1.services.PlaylistService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/channel")
public class ChannelController {
	
	private final ChannelService channelService;
	private final PlaylistService playlistService;
	private final ChannelPlaylistService channelPlaylistService;

	@PostMapping()
	public ChannelDto saveChannel(@Valid @RequestBody ChannelDto channelDto) {
		Channel channel = channelService.saveChannel(ChannelMapper.INSTANCE.fromDto(channelDto));
		return ChannelMapper.INSTANCE.toDto(channel);
	}
	
	@GetMapping("/all")
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
	public ChannelDto updateChannel(@PathVariable("id") long id,
									@Valid @RequestBody ChannelDto channelDto) {
		Channel channel = channelService.updateChannel(ChannelMapper.INSTANCE.fromDto(channelDto), id);
		return ChannelMapper.INSTANCE.toDto(channel);
	}
	
	@DeleteMapping("{id}")
	public void deleteChannel(@PathVariable("id") long id) {
		channelService.deleteChannel(id);
	}
	
	@PutMapping("/{channelId}/playlist/{playlistId}")
	public ChannelPlaylistGetDto addPlaylistToChannel(@PathVariable("channelId") long channelId,
													  @PathVariable("playlistId") long playlistId){
		return ChannelPlaylistGetMapper.INSTANCE.toDto(channelPlaylistService.addPlaylistToChannel(channelId, playlistService.getPlaylistById(playlistId)));
	}
	
	@DeleteMapping("/{channelId}/playlist/{playlistId}")
	public List<ChannelPlaylistGetDto> removePlaylistFromChannel(@PathVariable("channelId") long channelId,
																 @PathVariable("playlistId") long playlistId){
		return channelPlaylistService.removePlaylistFromChannel(channelId, playlistService.getPlaylistById(playlistId))
				.stream()
				.map(ChannelPlaylistGetMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{channelId}/sort")
	public List<ChannelPlaylistGetDto> sortPlaylistsInChannel(@PathVariable("channelId") long channelId) {
		return channelPlaylistService.sortPlaylists(channelService.getChannelById(channelId))
				.stream()
				.map(ChannelPlaylistGetMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
	
	@PutMapping("/{channelId}/playlist/{playlistId}/{newPosition}")
	public List<ChannelPlaylistGetDto> changePositionOfVideoInPlaylist(@PathVariable("channelId") long channelId,
																	   @PathVariable("playlistId") long playlistId,
																	   @PathVariable("newPosition") int newPosition) {
		return channelPlaylistService.changeIndexOfPlaylistInChannel(channelId, playlistService.getPlaylistById(playlistId), newPosition)
				.stream()
				.map(ChannelPlaylistGetMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
}
