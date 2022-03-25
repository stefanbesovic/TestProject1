package com.practice.test1.web.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.practice.test1.web.dto.playlist.PlaylistDto;
import com.practice.test1.web.dto.playlist.PlaylistGetDto;
import com.practice.test1.web.dto.playlist.PlaylistMapper;
import com.practice.test1.web.dto.playlistvideo.PlaylistVideoGetDto;
import com.practice.test1.web.dto.playlistvideo.PlaylistVideoMapper;
import com.practice.test1.services.PlaylistVideoService;
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

import com.practice.test1.entities.Playlist;
import com.practice.test1.services.PlaylistService;
import com.practice.test1.services.VideoService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/playlist")
@Tag(name = "Playlist Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Playlist" +
        " as well as Adding, Deleting, Sorting, Moving of Videos" +
        " and Adding and Removing of Categories from Playlist.")
public class PlaylistController {

    private final PlaylistService playlistService;
    private final PlaylistVideoService playlistVideoService;
    private final VideoService videoService;

    @Operation(summary = "Creates new Playlist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Playlist"),
            @ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping()
    public PlaylistDto savePlaylist(@Valid @RequestBody PlaylistGetDto playlistGetDto) {
        Playlist playlist = playlistService.savePlaylist(PlaylistMapper.INSTANCE.fromGetDto(playlistGetDto));
        return PlaylistMapper.INSTANCE.toDto(playlist);
    }

    @Operation(summary = "Retrieves list of all Playlists")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of Playlists"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/all")
    public List<PlaylistDto> getAllPlaylists() {
        return playlistService.getAllPlaylists()
                .stream()
                .map(PlaylistMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Retrieves details about Playlist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Playlist"),
            @ApiResponse(responseCode = "404", description = "Playlist not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("{id}")
    public PlaylistDto getPlaylistById(@PathVariable("id") Long id) {
        return PlaylistMapper.INSTANCE.toDto(playlistService.getPlaylistById(id));
    }

    @Operation(summary = "Updates existing Playlist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Playlist"),
            @ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
            @ApiResponse(responseCode = "404", description = "Playlist not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("{id}")
    public PlaylistDto updatePlaylist(@Valid @RequestBody PlaylistGetDto playlistGetDto,
                                      @PathVariable("id") Long id) {
        Playlist playlist = playlistService.updatePlaylist(PlaylistMapper.INSTANCE.fromGetDto(playlistGetDto), id);
        return PlaylistMapper.INSTANCE.toDto(playlist);
    }

    @Operation(summary = "Deletes Playlist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Playlist deleted"),
            @ApiResponse(responseCode = "404", description = "Playlist not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("{id}")
    public void deletePlaylist(@PathVariable("id") Long id) {
        playlistService.deletePlaylist(id);
    }

    @Operation(summary = "Add Category to Playlist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Playlist"),
            @ApiResponse(responseCode = "404", description = "Playlist or Category not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{playlistId}/category/{categoryId}")
    public PlaylistDto addCategory(@PathVariable("playlistId") Long playlistId,
                                   @PathVariable("categoryId") Long categoryId) {
        return PlaylistMapper.INSTANCE.toDto(playlistService.addCategory(playlistId, categoryId));
    }

    @Operation(summary = "Removes Category from Playlist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category deleted"),
            @ApiResponse(responseCode = "404", description = "Playlist or Category not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{playlistId}/category/{categoryId}")
    public void removeCategory(@PathVariable("playlistId") Long playlistId,
                               @PathVariable("categoryId") Long categoryId) {
        playlistService.RemoveCategory(playlistId, categoryId);
    }

    @Operation(summary = "Add Video to Playlist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Playlist"),
            @ApiResponse(responseCode = "404", description = "Video or Playlist not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{playlistId}/video/{videoId}")
    public PlaylistVideoGetDto addVideoToPlaylist(@PathVariable("playlistId") Long playlistId,
                                                  @PathVariable("videoId") Long videoId) {
        return PlaylistVideoMapper.INSTANCE.toDto(playlistVideoService.addVideoToPlaylist(playlistId, videoService.getVideoById(videoId)));
    }

    @Operation(summary = "Deletes Video from Playlist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Video deleted"),
            @ApiResponse(responseCode = "404", description = "Video or Playlist not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{playlistId}/video/{videoId}")
    public List<PlaylistVideoGetDto> removeVideoFromPlaylist(@PathVariable("playlistId") Long playlistId,
                                                             @PathVariable("videoId") Long videoId) {
        return playlistVideoService.removeVideoFromPlaylist(playlistId, videoService.getVideoById(videoId))
                .stream()
                .map(PlaylistVideoMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Retrieves sorted list of Videos in Playlist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sorted list of Videos"),
            @ApiResponse(responseCode = "404", description = "Playlist not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{playlistId}/sort")
    public List<PlaylistVideoGetDto> sortVideosInPlaylist(@PathVariable("playlistId") Long playlistId) {
        return playlistVideoService.sortVideos(playlistService.getPlaylistById(playlistId))
                .stream()
                .map(PlaylistVideoMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Changes order of Videos in Playlist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of Videos"),
            @ApiResponse(responseCode = "404", description = "Playlist or Video not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{playlistId}/video/{videoId}/{newPosition}")
    public List<PlaylistVideoGetDto> changePositionOfVideoInPlaylist(@PathVariable("playlistId") Long playlistId,
                                                                     @PathVariable("videoId") Long videoId,
                                                                     @PathVariable("newPosition") int newPosition) {
        return playlistVideoService.changeIndexOfVideoInPlaylist(playlistId, videoService.getVideoById(videoId), newPosition)
                .stream()
                .map(PlaylistVideoMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Add Playlist to User")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of User's playlists"),
            @ApiResponse(responseCode = "404", description = "Playlist or User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{playlistId}/user/{userId}")
    public Set<PlaylistDto> addPlaylistToUser(@PathVariable("playlistId") Long playlistId,
                                              @PathVariable("userId") Long userId) {
        return playlistService.addPlaylistToUser(playlistId, userId)
                .stream()
                .map(PlaylistMapper.INSTANCE::toDto)
                .collect(Collectors.toSet());
    }

    @Operation(summary = "Delete Playlist from User")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Playlist deleted"),
            @ApiResponse(responseCode = "404", description = "Playlist not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{playlistId}/user")
    public void removePlaylistFromUser(@PathVariable("playlistId") Long playlistId) {
        playlistService.removePlaylistFromUser(playlistId);
    }
}
