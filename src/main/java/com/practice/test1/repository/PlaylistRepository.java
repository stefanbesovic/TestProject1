package com.practice.test1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.test1.domen.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}
