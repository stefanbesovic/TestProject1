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

import com.practice.test1.entities.Channel;
import com.practice.test1.entities.Playlist;
import com.practice.test1.entities.ChannelPlaylist;
import com.practice.test1.services.ChannelService;
import com.practice.test1.services.ChannelPlaylistService;

@RequiredArgsConstructor
@Service
public class ChannelPlaylistServiceImplementation implements ChannelPlaylistService {
	
	private final ChannelService channelService;
	private static final Logger log = LoggerFactory.getLogger(ChannelPlaylistServiceImplementation.class);

	@Override
	public List<Playlist> sortPlaylists(Channel channel) {
		log.info("Sorting playlists in channel with id {}.", channel.getId());

		channel.getPlaylists().sort(Comparator.comparingInt(ChannelPlaylist::getPosition));

		return channel.getPlaylists().stream()
				.map(ChannelPlaylist::getPlaylist)
				.collect(Collectors.toList());
	}

	@Override
	public Channel addPlaylistToChannel(long channelId, Playlist playlist) {
		Channel channel = channelService.getChannelById(channelId);

		ChannelPlaylist channelPlaylist = new ChannelPlaylist(channel, playlist, channel.getPlaylists().size() + 1);
		channel.getPlaylists().add(channelPlaylist);

		log.info("Adding video {} to playlist {}.", playlist.getId(), channel.getId());

		return channelService.updateChannel(channel, channelId);
	}

	@Override
	public void removePlaylistFromChannel(long channelId, Playlist playlist) {
		log.info("Removing video {} from playlist {}", playlist.getId(), channelId);

		int index = 0;
		Channel channel = channelService.getChannelById(channelId);

		for(ChannelPlaylist o : channel.getPlaylists()) {
			if(o.getPlaylist().equals(playlist)) {
				log.debug("Found the playlist in channel.");
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
		log.info("Changing position of playlist {} in channel {}.", playlist.getId(), channelId);

		int rangeFrom ;
        int rangeTo;
        int directionValue;

        Channel channel = channelService.getChannelById(channelId);
        ChannelPlaylist order = channel.getPlaylists().stream().filter(x -> x.getPlaylist().equals(playlist)).findAny()
        		.orElseThrow(() -> new NoSuchElementException(String.format("Can't change index of playlist in channel. Playlist not found: %d", playlist.getId())));

        int currentPosition = order.getPosition();

		if(newPosition > channel.getPlaylists().size())
			throw new IndexOutOfBoundsException(String.format("Position out of bounds. Position %d - Actual channel size %d", newPosition, channel.getPlaylists().size()));

		if(currentPosition == newPosition) {
			log.debug("Current position of playlist is same as the new position.");
			return;
		}

		if(currentPosition > newPosition) {
			log.debug("Moving playlists to right.");
			rangeFrom = newPosition;
            rangeTo = currentPosition;
            directionValue = 1;
        } else {
			log.debug("Moving playlists to left.");
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

		log.info("Position of playlist {} has changed from {} to {}.", playlist.getId(), currentPosition, newPosition);

		channelService.updateChannel(channel, channelId);
	}
}
