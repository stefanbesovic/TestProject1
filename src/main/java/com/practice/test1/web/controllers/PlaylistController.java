package com.practice.test1.web.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.practice.test1.web.dto.playlist.PlaylistDto;
import com.practice.test1.web.dto.playlist.PlaylistGetDto;
import com.practice.test1.web.dto.playlist.PlaylistGetMapper;
import com.practice.test1.web.dto.playlist.PlaylistMapper;
import com.practice.test1.web.dto.playlistvideo.PlaylistVideoGetDto;
import com.practice.test1.web.dto.playlistvideo.PlaylistVideoGetMapper;
import com.practice.test1.services.PlaylistVideoService;
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
public class PlaylistController {

    private final PlaylistService playlistService;
    private final PlaylistVideoService playlistVideoService;
    private final VideoService videoService;

    @PostMapping()
    public PlaylistDto savePlaylist(@Valid @RequestBody PlaylistGetDto playlistGetDto) {
        Playlist playlist = playlistService.savePlaylist(PlaylistGetMapper.INSTANCE.fromDto(playlistGetDto));
        return PlaylistMapper.INSTANCE.toDto(playlist);
    }

    @GetMapping("/all")
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
    public PlaylistDto updatePlaylist(@Valid @RequestBody PlaylistGetDto playlistGetDto,
                                      @PathVariable("id") long id) {
        Playlist playlist = playlistService.updatePlaylist(PlaylistGetMapper.INSTANCE.fromDto(playlistGetDto), id);
        return PlaylistMapper.INSTANCE.toDto(playlist);
    }

    @DeleteMapping("{id}")
    public void deletePlaylist(@PathVariable("id") long id) {
        playlistService.deletePlaylist(id);
    }

    @PutMapping("/{playlistId}/category/{categoryId}")
    public PlaylistDto addCategory(@PathVariable("playlistId") long playlistId,
                                   @PathVariable("categoryId") long categoryId) {
        return PlaylistMapper.INSTANCE.toDto(playlistService.addCategory(playlistId, categoryId));
    }

    @DeleteMapping("/{playlistId}/category/{categoryId}")
    public void removeCategory(@PathVariable("playlistId") long playlistId,
                               @PathVariable("categoryId") long categoryId) {
        playlistService.RemoveCategory(playlistId, categoryId);
    }

    @PutMapping("/{playlistId}/video/{videoId}")
    public PlaylistVideoGetDto addVideoToPlaylist(@PathVariable("playlistId") long playlistId,
                                                  @PathVariable("videoId") long videoId) {
        return PlaylistVideoGetMapper.INSTANCE.toDto(playlistVideoService.addVideoToPlaylist(playlistId, videoService.getVideoById(videoId)));
    }

    @DeleteMapping("/{playlistId}/video/{videoId}")
    public List<PlaylistVideoGetDto> removeVideoFromPlaylist(@PathVariable("playlistId") long playlistId,
                                                             @PathVariable("videoId") long videoId) {
        return playlistVideoService.removeVideoFromPlaylist(playlistId, videoService.getVideoById(videoId))
                .stream()
                .map(PlaylistVideoGetMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{playlistId}/sort")
    public List<PlaylistVideoGetDto> sortVideosInPlaylist(@PathVariable("playlistId") long playlistId) {
        return playlistVideoService.sortVideos(playlistService.getPlaylistById(playlistId))
                .stream()
                .map(PlaylistVideoGetMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{playlistId}/video/{videoId}/{newPosition}")
    public List<PlaylistVideoGetDto> changePositionOfVideoInPlaylist(@PathVariable("playlistId") long playlistId,
                                                                     @PathVariable("videoId") long videoId,
                                                                     @PathVariable("newPosition") int newPosition) {
        return playlistVideoService.changeIndexOfVideoInPlaylist(playlistId, videoService.getVideoById(videoId), newPosition)
                .stream()
                .map(PlaylistVideoGetMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{playlistId}/user/{userId}")
    public Set<PlaylistDto> addPlaylistToUser(@PathVariable("playlistId") long playlistId,
                                              @PathVariable("userId") long userId) {
        return playlistService.addPlaylistToUser(playlistId, userId)
                .stream()
                .map(PlaylistMapper.INSTANCE::toDto)
                .collect(Collectors.toSet());
    }

    @DeleteMapping("/{playlistId}/user")
    public void removePlaylistFromUser(@PathVariable("playlistId") long playlistId) {
        playlistService.removePlaylistFromUser(playlistId);
    }
}
