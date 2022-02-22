package com.practice.test1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.test1.domen.Channel;
import com.practice.test1.service.ChannelService;

@RestController
@RequestMapping("api/channels")
public class ChannelController {
	
	private ChannelService channelService;
	
	public ChannelController(ChannelService channelService) {
		super();
		this.channelService = channelService;
	}

	@PostMapping()
	public ResponseEntity<Channel> saveChannel(@RequestBody Channel channel) {
		return new ResponseEntity<Channel>(channelService.saveChannel(channel), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public List<Channel> getAllChannels() {
		return channelService.getAllChannels();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Channel> getChannelById(@PathVariable("id") long id) {
		return new ResponseEntity<Channel>(channelService.getChannelById(id), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Channel> updateChannel(@PathVariable("id") long id, @RequestBody Channel channel) {
		return new ResponseEntity<Channel>(channelService.updateChannel(channel, id), HttpStatus.OK);
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> DeleteChannel(@PathVariable("id") long id) {
		channelService.deleteChannel(id);
		
		return new ResponseEntity<String>("Channel deleted successfully!", HttpStatus.OK);
	}
}
