package com.practice.test1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.test1.entities.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
