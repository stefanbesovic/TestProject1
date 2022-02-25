package com.practice.test1.services.implementation;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.practice.test1.entities.Channel;
import com.practice.test1.entities.Playlist;
import com.practice.test1.entities.PlaylistOrder;
import com.practice.test1.services.ChannelService;
import com.practice.test1.services.PlaylistOrderService;

@RequiredArgsConstructor
@Service
public class PlaylistOrderServiceImplementation implements PlaylistOrderService{
	
	private final ChannelService channelService;

	@Override
	public List<Playlist> sortPlaylists(Channel channel) {
		channel.getPlaylists().sort(Comparator.comparingInt(PlaylistOrder::getPosition));
		return channel.getPlaylists().stream()
				.map(PlaylistOrder::getPlaylist)
				.collect(Collectors.toList());
	}

	@Override
	public Channel addPlaylistToChannel(long channelId, Playlist playlist) {
		Channel channel = channelService.getChannelById(channelId);
		PlaylistOrder playlistOrder = new PlaylistOrder(channel, playlist, channel.getPlaylists().size() + 1);
		channel.getPlaylists().add(playlistOrder);
		return channelService.updateChannel(channel, channelId);
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
		AtomicInteger i = new AtomicInteger(index);
		channel.getPlaylists()
				.stream()
				.filter(x -> x.getPosition() >= i.get())
				.forEach(x -> x.setPosition(x.getPosition() - 1));
		channelService.updateChannel(channel, channelId);
	}

	@Override
	public void changeIndexOfPlaylistInChannel(long channelId, Playlist playlist, int newPosition) {
		int rangeFrom ;
        int rangeTo;
        int directionValue;
        Channel channel = channelService.getChannelById(channelId);
        PlaylistOrder order = channel.getPlaylists().stream().filter(x -> x.getPlaylist().equals(playlist)).findAny()
        		.orElseThrow(() -> new NoSuchElementException(String.format("Can't change index of playlist in channel. Playlist not found: %d", playlist.getId())));
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
        channel.getPlaylists().stream()
            .filter(x -> x.getPosition() >= rangeF.get() && x.getPosition() < rangeT.get())
            .forEach(x -> x.setPosition(x.getPosition() + dir.get()));
        order.setPosition(newPosition);
        channel.getPlaylists().sort(Comparator.comparingInt(PlaylistOrder::getPosition));
        channelService.updateChannel(channel, channelId);
	}
}
