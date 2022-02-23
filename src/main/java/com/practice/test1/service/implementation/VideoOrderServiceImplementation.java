package com.practice.test1.service.implementation;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.practice.test1.domen.Playlist;
import com.practice.test1.domen.Video;
import com.practice.test1.domen.VideoOrder;
import com.practice.test1.repository.PlaylistRepository;
import com.practice.test1.repository.VideoRepository;
import com.practice.test1.service.VideoOrderService;

@Service
public class VideoOrderServiceImplementation implements VideoOrderService{

	private VideoRepository videoRepository;
	private PlaylistRepository playlistRepository;
	
	
	public VideoOrderServiceImplementation(VideoRepository videoRepository,
			PlaylistRepository playlistRepository) {
		super();
		this.videoRepository = videoRepository;
		this.playlistRepository = playlistRepository;
	}

	@Override
	public void sortVideos(Playlist playlist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Playlist addVideoToPlaylist(long playlistId, Video video) {
		Playlist playlist = playlistRepository.findById(playlistId).orElse(null);
		if(playlist.equals(null)) {
			return null;
		}
		VideoOrder videoOrder = new VideoOrder();
		videoOrder.setPlaylist(playlist);
		videoOrder.setVideo(video);
		videoOrder.setPosition(playlist.getVideos().size() + 1);
		playlist.getVideos().add(videoOrder);
		
		return playlistRepository.save(playlist);
	}

	@Override
	public void RemoveVideoFromPlaylist(long playlistId, Video video) {
		int index = 0;
		Playlist playlist = playlistRepository.findById(playlistId).orElse(null);
		if(playlist == null) {
			return;
		}
		for(VideoOrder o : playlist.getVideos())
		{
			if(o.getVideo().equals(video))
			{
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
		playlistRepository.save(playlist);
	}

	@Override
	public void ChangeIndexOfVideoInPlaylist(long playlistId, Video video, long newPlace) {
		// TODO Auto-generated method stub
		
	}

}
