package com.practice.test1.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.test1.domen.Channel;
import com.practice.test1.repository.ChannelRepository;
import com.practice.test1.service.ChannelService;

@Service
public class ChannelServiceImplementation implements ChannelService{
	
	private ChannelRepository channelRepository;
	
	public ChannelServiceImplementation(ChannelRepository channelRepository) {
		super();
		this.channelRepository = channelRepository;
	}

	@Override
	public Channel saveChannel(Channel channel) {
		if(!channelRepository.existsById(channel.getId())) {
			return channelRepository.save(channel);
		}else {
			return null;
		}
	}

	@Override
	public List<Channel> getAllChannels() {
		return channelRepository.findAll();
	}

	@Override
	public Channel getChannelById(long id) {
		return channelRepository.findById(id).orElse(null);
	}

	@Override
	public Channel updateChannel(Channel channel, long id) {
		Channel existing = channelRepository.findById(id).orElse(null);
		if(existing.equals(null)) {
			return null;
		}
		existing.setName(channel.getName());
		channelRepository.save(existing);
		return existing;
	}

	@Override
	public void deleteChannel(long id) {
		Channel exists = channelRepository.findById(id).orElse(null);
		if(exists.equals(null)) {
			return;
		}
		channelRepository.deleteById(id);
	}

}
