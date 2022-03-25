package com.practice.test1.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.test1.web.dto.video.VideoDto;
import com.practice.test1.web.dto.video.VideoMapper;
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

import com.practice.test1.entities.Video;
import com.practice.test1.services.VideoService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/video")
@Tag(name = "Video Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Video" +
        " as well as Adding and Removing of Categories from Video.")
public class VideoController {

    private final VideoService videoService;

    @Operation(summary = "Creates new Video")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Video"),
            @ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping()
    public VideoDto saveVideo(@Valid @RequestBody VideoDto videoDto) {
        Video video = videoService.saveVideo(VideoMapper.INSTANCE.fromDto(videoDto));
        return VideoMapper.INSTANCE.toDto(video);
    }

    @Operation(summary = "Retrieves list of all Videos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of Videos"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/all")
    public List<VideoDto> getAllVideos() {
        return videoService.getAllVideos()
                .stream()
                .map(VideoMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Retrieves details about Video")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Video"),
            @ApiResponse(responseCode = "404", description = "Video not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("{id}")
    public VideoDto getVideoById(@PathVariable("id") Long id) {
        return VideoMapper.INSTANCE.toDto(videoService.getVideoById(id));
    }

    @Operation(summary = "Updates existing Video")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Video"),
            @ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
            @ApiResponse(responseCode = "404", description = "Video not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("{id}")
    public VideoDto updateVideo(@Valid @RequestBody VideoDto videoDto,
                                @PathVariable("id") Long id) {
        Video video = videoService.updateVideo(VideoMapper.INSTANCE.fromDto(videoDto), id);
        return VideoMapper.INSTANCE.toDto(video);
    }

    @Operation(summary = "Deletes Video")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Video deleted"),
            @ApiResponse(responseCode = "404", description = "Video not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("{id}")
    public void deleteVideo(@PathVariable("id") Long id) {
        videoService.deleteVideo(id);
    }

    @Operation(summary = "Add Category to Video")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Video"),
            @ApiResponse(responseCode = "404", description = "Video or Category not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{videoId}/category/{categoryId}")
    public VideoDto addCategory(@PathVariable("videoId") Long videoId,
                                @PathVariable("categoryId") Long categoryId) {
        return VideoMapper.INSTANCE.toDto(videoService.addCategory(videoId, categoryId));
    }

    @Operation(summary = "Delete Category from Video")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category deleted"),
            @ApiResponse(responseCode = "404", description = "Video or Category not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{videoId}/category/{categoryId}")
    public void removeCategory(@PathVariable("videoId") Long videoId,
                                   @PathVariable("categoryId") Long categoryId) {
        videoService.RemoveCategory(videoId, categoryId);
    }
}
