package com.practice.test1.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.test1.entities.Channel;
import com.practice.test1.web.dto.channel.ChannelDto;
import com.practice.test1.web.dto.channel.ChannelMapper;
import com.practice.test1.web.dto.channel.ChannelResponseDto;
import com.practice.test1.web.dto.channelplaylist.ChannelPlaylistGetDto;
import com.practice.test1.web.dto.channelplaylist.ChannelPlaylistGetMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Channel Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Channel" +
												" as well as Adding, Deleting, Sorting, Moving of Playlists.")
public class ChannelController {
	
	private final ChannelService channelService;
	private final PlaylistService playlistService;
	private final ChannelPlaylistService channelPlaylistService;

	@Operation(summary = "Creates new Channel")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Channel"),
			@ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@PostMapping()
	public ChannelResponseDto saveChannel(@Valid @RequestBody ChannelDto channelDto) {
		Channel channel = channelService.saveChannel(ChannelMapper.INSTANCE.fromDto(channelDto));
		return ChannelMapper.INSTANCE.toResDto(channel);
	}

	@Operation(summary = "Retrieves list of all Channels")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "List of Channels"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping("/all")
	public List<ChannelResponseDto> getAllChannels() {
		return channelService.getAllChannels()
				.stream().map(ChannelMapper.INSTANCE::toResDto)
				.collect(Collectors.toList());
	}

	@Operation(summary = "Retrieves details about Channel")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Channel"),
			@ApiResponse(responseCode = "404", description = "Channel not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping("{id}")
	public ChannelResponseDto getChannelById(@PathVariable("id") Long id) {
		return ChannelMapper.INSTANCE.toResDto(channelService.getChannelById(id));
	}

	@Operation(summary = "Updates existing Channel")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Channel"),
			@ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
			@ApiResponse(responseCode = "404", description = "Channel not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@PutMapping("{id}")
	public ChannelResponseDto updateChannel(@PathVariable("id") Long id,
									@Valid @RequestBody ChannelDto channelDto) {
		Channel channel = channelService.updateChannel(ChannelMapper.INSTANCE.fromDto(channelDto), id);
		return ChannelMapper.INSTANCE.toResDto(channel);
	}

	@Operation(summary = "Deletes Channel")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Channel deleted"),
			@ApiResponse(responseCode = "404", description = "Channel not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@DeleteMapping("{id}")
	public void deleteChannel(@PathVariable("id") Long id) {
		channelService.deleteChannel(id);
	}

	@Operation(summary = "Add Playlist to Channel")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Channel"),
			@ApiResponse(responseCode = "404", description = "Channel or Playlist not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@PutMapping("/{channelId}/playlist/{playlistId}")
	public ChannelPlaylistGetDto addPlaylistToChannel(@PathVariable("channelId") Long channelId,
													  @PathVariable("playlistId") Long playlistId){
		return ChannelPlaylistGetMapper.INSTANCE.toDto(channelPlaylistService.addPlaylistToChannel(channelId, playlistService.getPlaylistById(playlistId)));
	}

	@Operation(summary = "Deletes Playlist from Channel")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Playlist deleted"),
			@ApiResponse(responseCode = "404", description = "Channel or Playlist not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@DeleteMapping("/{channelId}/playlist/{playlistId}")
	public List<ChannelPlaylistGetDto> removePlaylistFromChannel(@PathVariable("channelId") Long channelId,
																 @PathVariable("playlistId") Long playlistId){
		return channelPlaylistService.removePlaylistFromChannel(channelId, playlistService.getPlaylistById(playlistId))
				.stream()
				.map(ChannelPlaylistGetMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}

	@Operation(summary = "Retrieves sorted list of Playlists in Channel")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Sorted list of Playlists"),
			@ApiResponse(responseCode = "404", description = "Channel not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping("/{channelId}/sort")
	public List<ChannelPlaylistGetDto> sortPlaylistsInChannel(@PathVariable("channelId") Long channelId) {
		return channelPlaylistService.sortPlaylists(channelService.getChannelById(channelId))
				.stream()
				.map(ChannelPlaylistGetMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}

	@Operation(summary = "Changes order of Playlists in Channel")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "List of Playlists"),
			@ApiResponse(responseCode = "404", description = "Channel or Playlist not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@PutMapping("/{channelId}/playlist/{playlistId}/{newPosition}")
	public List<ChannelPlaylistGetDto> changePositionOfVideoInPlaylist(@PathVariable("channelId") Long channelId,
																	   @PathVariable("playlistId") Long playlistId,
																	   @PathVariable("newPosition") int newPosition) {
		return channelPlaylistService.changeIndexOfPlaylistInChannel(channelId, playlistService.getPlaylistById(playlistId), newPosition)
				.stream()
				.map(ChannelPlaylistGetMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
}
