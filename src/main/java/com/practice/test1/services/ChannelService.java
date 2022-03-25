package com.practice.test1.services;

import java.util.List;

import com.practice.test1.entities.Channel;

public interface ChannelService {
	Channel saveChannel(Channel channel);
	List<Channel> getAllChannels();
	Channel getChannelById(Long id);
	Channel updateChannel(Channel channel, Long id);
	void deleteChannel(Long id);
}
