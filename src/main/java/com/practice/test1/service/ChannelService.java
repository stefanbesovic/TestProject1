package com.practice.test1.service;

import java.util.List;

import com.practice.test1.domen.Channel;

public interface ChannelService {
	Channel saveChannel(Channel channel);
	List<Channel> getAllChannels();
	Channel getChannelById(long id);
	Channel updateChannel(Channel channel, long id);
	void deleteChannel(long id);
}
