package com.practice.test1.services;

import java.util.List;

import com.practice.test1.entities.Channel;

public interface ChannelService {
	Channel saveChannel(Channel channel);
	List<Channel> getAllChannels();
	Channel getChannelById(long id);
	Channel updateChannel(Channel channel, long id);
	void deleteChannel(long id);
}
