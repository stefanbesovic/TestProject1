package com.practice.test1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.test1.domen.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

}
