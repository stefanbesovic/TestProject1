package com.practice.test1.services.implementation;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.practice.test1.entities.Playlist;
import com.practice.test1.entities.Video;
import com.practice.test1.entities.VideoOrder;
import com.practice.test1.services.PlaylistService;
import com.practice.test1.services.VideoOrderService;

@RequiredArgsConstructor
@Service
public class VideoOrderServiceImplementation implements VideoOrderService{

	private final PlaylistService playlistService;

	@Override
	public List<Video> sortVideos(Playlist playlist) {
		playlist.getVideos().sort(Comparator.comparingInt(VideoOrder::getPosition));
		return playlist.getVideos().stream()
				.map(VideoOrder::getVideo)
				.collect(Collectors.toList());
	}

	@Override
	public Playlist addVideoToPlaylist(long playlistId, Video video) {
		Playlist playlist = playlistService.getPlaylistById(playlistId);
		VideoOrder videoOrder = new VideoOrder(playlist, video, playlist.getVideos().size() + 1);
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
		AtomicInteger i = new AtomicInteger(index);
		playlist.getVideos()
				.stream()
				.filter(x -> x.getPosition() >= i.get())
				.forEach(x -> x.setPosition(x.getPosition() - 1));
		playlistService.updatePlaylist(playlist, playlistId);
	}

	@Override
	public void changeIndexOfVideoInPlaylist(long playlistId, Video video, int newPosition) {
		int rangeFrom;
        int rangeTo;
        int directionValue;
		Playlist playlist = playlistService.getPlaylistById(playlistId);
        VideoOrder order = playlist.getVideos().stream().filter(x -> x.getVideo().equals(video)).findAny()
				.orElseThrow(() -> new NoSuchElementException(String.format("Can't change index of video in playlist. Video not found: %d", video.getId())));
        int currentPosition = order.getPosition();
		if(currentPosition == newPosition) return;
        if(currentPosition > newPosition) {
            rangeFrom = newPosition;
            rangeTo = currentPosition;
            directionValue = 1;
        }else {
            rangeFrom = currentPosition + 1;
            rangeTo = newPosition + 1;
            directionValue = -1;
        }
		AtomicInteger rangeF = new AtomicInteger(rangeFrom);
		AtomicInteger rangeT = new AtomicInteger(rangeTo);
		AtomicInteger dir = new AtomicInteger(directionValue);
        playlist.getVideos().stream()
            .filter(x -> x.getPosition() >= rangeF.get() && x.getPosition() < rangeT.get())
            .forEach(x -> x.setPosition(x.getPosition() + dir.get()));
        order.setPosition(newPosition);
        playlist.getVideos().sort(Comparator.comparingInt(VideoOrder::getPosition));
        playlistService.updatePlaylist(playlist, playlistId);
	}
}
