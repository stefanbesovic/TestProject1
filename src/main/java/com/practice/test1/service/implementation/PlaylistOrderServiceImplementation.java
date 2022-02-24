package com.practice.test1.service.implementation;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.practice.test1.domen.Channel;
import com.practice.test1.domen.Playlist;
import com.practice.test1.domen.PlaylistOrder;
import com.practice.test1.service.ChannelService;
import com.practice.test1.service.PlaylistOrderService;

@Service
public class PlaylistOrderServiceImplementation implements PlaylistOrderService{
	
	private final ChannelService channelService;
	
	public PlaylistOrderServiceImplementation(ChannelService channelService) {
		super();
		this.channelService = channelService;
	}

	@Override
	public List<Playlist> sortPlaylists(Channel channel) {
		Collections.sort(channel.getPlaylists(), (x, y) -> x.getPosition() - y.getPosition());
		channelService.saveChannel(channel);
		return channel.getPlaylists().stream()
				.map(x -> x.getPlaylist())
				.collect(Collectors.toList());
	}

	@Override
	public Channel addPlaylistToChannel(long channelId, Playlist playlist) {
		Channel channel = channelService.getChannelById(channelId);
		PlaylistOrder playlistOrder = new PlaylistOrder();
		playlistOrder.setChannel(channel);
		playlistOrder.setPlaylist(playlist);
		playlistOrder.setPosition(channel.getPlaylists().size() + 1);
		channel.getPlaylists().add(playlistOrder);
		return channelService.saveChannel(channel);
	}

	@Override
	public void removePlaylistFromChannel(long channelId, Playlist playlist) {
		int index = 0;
		Channel channel = channelService.getChannelById(channelId);
		for(PlaylistOrder o : channel.getPlaylists()) {
			if(o.getPlaylist().equals(playlist)) {
				index = o.getPosition();
				channel.getPlaylists().remove(index - 1);
				break;
			}
		}
		final int i = index;
		channel.getPlaylists()
				.stream()
				.filter(x -> x.getPosition() >= i)
				.forEach(x -> x.setPosition(x.getPosition() - 1));
		channelService.saveChannel(channel);
	}

	@Override
	public void changeIndexOfPlaylistInChannel(long channelId, Playlist playlist, int newPosition) {
		int rangeFrom = 0;
        int rangeTo = 0;
        int directionValue = 0;
        Channel channel = channelService.getChannelById(channelId);
        PlaylistOrder order = channel.getPlaylists().stream().filter(x -> x.getPlaylist().equals(playlist)).findAny()
        		.orElseThrow(() -> new NoSuchElementException(String.format("Can't change index of playlist in channel. Playlist not found: %d", playlist.getId())));
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
        channel.getPlaylists().stream()
            .filter(x -> x.getPosition() >= rangeF && x.getPosition() < rangeT)
            .forEach(x -> x.setPosition(x.getPosition() + dir));
        order.setPosition(newPosition);
        Collections.sort(channel.getPlaylists(), (x, y) -> x.getPosition() - y.getPosition());
        channelService.saveChannel(channel);
	}
}
