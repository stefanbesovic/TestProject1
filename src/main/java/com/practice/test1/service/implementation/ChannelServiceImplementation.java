package com.practice.test1.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.practice.test1.domen.Channel;
import com.practice.test1.repository.ChannelRepository;
import com.practice.test1.service.ChannelService;

@Service
public class ChannelServiceImplementation implements ChannelService{
	
	private final ChannelRepository channelRepository;
	
	public ChannelServiceImplementation(ChannelRepository channelRepository) {
		this.channelRepository = channelRepository;
	}

	@Override
	public Channel saveChannel(Channel channel) {
		if(!channelRepository.existsById(channel.getId())) {
			return channelRepository.save(channel);
		}else {
			throw new DuplicateKeyException("Could not save channel. Channel with same ID already exists.");
		}
	}

	@Override
	public List<Channel> getAllChannels() {
		return channelRepository.findAll();
	}

	@Override
	public Channel getChannelById(long id) {
		return channelRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("Channel not found: %d", id)));
	}

	@Override
	public Channel updateChannel(Channel channel, long id) {
		Channel existing = getChannelById(id);
		existing.setName(channel.getName());
		channelRepository.save(existing);
		return existing;
	}

	@Override
	public void deleteChannel(long id) {
		getChannelById(id);
		channelRepository.deleteById(id);
	}
}
