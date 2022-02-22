package com.practice.test1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.test1.domen.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{

}
