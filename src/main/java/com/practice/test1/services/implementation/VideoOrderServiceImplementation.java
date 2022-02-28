package com.practice.test1.services.implementation;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger log = LoggerFactory.getLogger(VideoOrderServiceImplementation.class);

	@Override
	public List<Video> sortVideos(Playlist playlist) {
		log.info("Sorting videos in playlist with id {}.", playlist.getId());
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
		log.info("Adding video {} to playlist {}.", video.getId(), playlist.getId());
		return playlistService.updatePlaylist(playlist, playlistId);
	}

	@Override
	public void removeVideoFromPlaylist(long playlistId, Video video) {
		log.info("Removing video {} from playlist {}", video.getId(), playlistId);
		int index = 0;
		Playlist playlist = playlistService.getPlaylistById(playlistId);
		for(VideoOrder o : playlist.getVideos()) {
			if(o.getVideo().equals(video)) {
				log.debug("Found the video in playlist");
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
		log.info("Changing position of video {} in playlist {}.", video.getId(), playlistId);
		int rangeFrom;
        int rangeTo;
        int directionValue;
		Playlist playlist = playlistService.getPlaylistById(playlistId);
        VideoOrder order = playlist.getVideos().stream().filter(x -> x.getVideo().equals(video)).findAny()
				.orElseThrow(() -> new NoSuchElementException(String.format("Can't change index of video in playlist. Video not found: %d", video.getId())));
        int currentPosition = order.getPosition();
		if(currentPosition == newPosition) {
			log.debug("Current position of video is same as the new position.");
			return;
		}
        if(currentPosition > newPosition) {
			log.debug("Moving videos to right.");
            rangeFrom = newPosition;
            rangeTo = currentPosition;
            directionValue = 1;
        }else {
			log.debug("Moving videos to left.");
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
		log.info("Position of video {} has changed from {} to {}.", video.getId(), currentPosition, newPosition);
        playlistService.updatePlaylist(playlist, playlistId);
	}
}
