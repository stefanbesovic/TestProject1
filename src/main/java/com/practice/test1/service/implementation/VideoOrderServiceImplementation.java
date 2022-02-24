package com.practice.test1.service.implementation;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.practice.test1.domen.Playlist;
import com.practice.test1.domen.Video;
import com.practice.test1.domen.VideoOrder;
import com.practice.test1.repository.PlaylistRepository;
import com.practice.test1.service.PlaylistService;
import com.practice.test1.service.VideoOrderService;

@Service
public class VideoOrderServiceImplementation implements VideoOrderService{

	private final PlaylistService playlistService;
	
	public VideoOrderServiceImplementation(PlaylistService playlistService) {
		super();
		this.playlistService = playlistService;
	}

	@Override
	public List<Video> sortVideos(Playlist playlist) {
		Collections.sort(playlist.getVideos(), (x, y) -> x.getPosition() - y.getPosition());
		playlistService.updatePlaylist(playlist, playlist.getId());
		return playlist.getVideos().stream()
				.map(x -> x.getVideo())
				.collect(Collectors.toList());
	}

	@Override
	public Playlist addVideoToPlaylist(long playlistId, Video video) {
		Playlist playlist = playlistService.getPlaylistById(playlistId);
		VideoOrder videoOrder = new VideoOrder();
		videoOrder.setPlaylist(playlist);
		videoOrder.setVideo(video);
		videoOrder.setPosition(playlist.getVideos().size() + 1);
		playlist.getVideos().add(videoOrder);
		return playlistService.updatePlaylist(playlist, playlistId);
	}

	@Override
	public void removeVideoFromPlaylist(long playlistId, Video video) {
		int index = 0;
		Playlist playlist = playlistService.getPlaylistById(playlistId);
		for(VideoOrder o : playlist.getVideos()) {
			if(o.getVideo().equals(video)) {
				index = o.getPosition();
				playlist.getVideos().remove(index - 1);
				break;
			}
		}
		final int i = index;
		playlist.getVideos()
				.stream()
				.filter(x -> x.getPosition() >= i)
				.forEach(x -> x.setPosition(x.getPosition() - 1));
		playlistService.updatePlaylist(playlist, playlistId);
	}

	@Override
	public void changeIndexOfVideoInPlaylist(long playlistId, Video video, int newPosition) {
		int rangeFrom = 0;
        int rangeTo = 0;
        int directionValue = 0;
		Playlist playlist = playlistService.getPlaylistById(playlistId);
        VideoOrder order = playlist.getVideos().stream().filter(x -> x.getVideo().equals(video)).findAny()
				.orElseThrow(() -> new NoSuchElementException(String.format("Can't change index of video in playlist. Video not found: %d", video.getId())));
        int currentPosition = order.getPosition();
        if(currentPosition > newPosition) {
            rangeFrom = newPosition;
            rangeTo = currentPosition;
            directionValue = 1;
        }else if (currentPosition < newPosition) {
            rangeFrom = currentPosition + 1;
            rangeTo = newPosition + 1;
            directionValue = -1;
        }else {
            return;
        }
        final int rangeF = rangeFrom;
        final int rangeT = rangeTo;
        final int dir = directionValue;
        playlist.getVideos().stream()
            .filter(x -> x.getPosition() >= rangeF && x.getPosition() < rangeT)
            .forEach(x -> x.setPosition(x.getPosition() + dir));
        order.setPosition(newPosition);
        Collections.sort(playlist.getVideos(), (x, y) -> x.getPosition() - y.getPosition());
        playlistService.updatePlaylist(playlist, playlistId);
	}
}
