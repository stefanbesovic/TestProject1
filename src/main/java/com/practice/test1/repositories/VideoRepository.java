package com.practice.test1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.test1.entities.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{
}
