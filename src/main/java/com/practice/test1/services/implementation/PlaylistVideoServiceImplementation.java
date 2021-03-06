package com.practice.test1.services.implementation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.practice.test1.entities.Playlist;
import com.practice.test1.entities.Video;
import com.practice.test1.entities.PlaylistVideo;
import com.practice.test1.services.PlaylistService;
import com.practice.test1.services.PlaylistVideoService;

@RequiredArgsConstructor
@Service
@Slf4j
public class PlaylistVideoServiceImplementation implements PlaylistVideoService {

	private final PlaylistService playlistService;

	@Override
	public List<PlaylistVideo> sortVideos(Playlist playlist) {
		log.info("Sorting videos in playlist with id {}.", playlist.getId());

		playlist.getVideos().sort(Comparator.comparingInt(PlaylistVideo::getPosition));

		return new ArrayList<>(playlist.getVideos());
	}

	@Override
	public PlaylistVideo addVideoToPlaylist(Long playlistId, Video video) {
		Playlist playlist = playlistService.getPlaylistById(playlistId);
		PlaylistVideo playlistVideo = new PlaylistVideo(playlist, video, playlist.getVideos().size() + 1);
		playlist.getVideos().add(playlistVideo);

		log.info("Adding video {} to playlist {}.", video.getId(), playlist.getId());

		playlistService.updatePlaylist(playlist, playlistId);
		return playlistVideo;
	}

	@Override
	public List<PlaylistVideo> removeVideoFromPlaylist(Long playlistId, Video video) {
		log.info("Removing video {} from playlist {}", video.getId(), playlistId);

		int index = 0;
		Playlist playlist = playlistService.getPlaylistById(playlistId);

		for(PlaylistVideo o : playlist.getVideos()) {
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
		return new ArrayList<>(playlist.getVideos());
	}

	@Override
	public List<PlaylistVideo> changeIndexOfVideoInPlaylist(Long playlistId, Video video, int newPosition) {
		log.info("Changing position of video {} in playlist {}.", video.getId(), playlistId);

		int rangeFrom;
        int rangeTo;
        int directionValue;

		Playlist playlist = playlistService.getPlaylistById(playlistId);
        PlaylistVideo order = playlist.getVideos().stream().filter(x -> x.getVideo().equals(video)).findAny()
				.orElseThrow(() -> new NoSuchElementException(String.format("Can't change index of video in playlist. Video not found: %d", video.getId())));
        int currentPosition = order.getPosition();

		if(newPosition > playlist.getVideos().size())
			throw new IndexOutOfBoundsException(String.format("Position out of bounds. Position %d - Actual playlist size %d", newPosition, playlist.getVideos().size()));

		if(currentPosition == newPosition) {
			log.debug("Current position of video is same as the new position.");
			return new ArrayList<>();
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

		log.info("Position of video {} has changed from {} to {}.", video.getId(), currentPosition, newPosition);

        playlistService.updatePlaylist(playlist, playlistId);

		return new ArrayList<>(playlistService.getPlaylistById(playlistId).getVideos());
	}
}
