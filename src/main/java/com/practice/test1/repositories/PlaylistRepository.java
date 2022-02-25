package com.practice.test1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.test1.entities.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}
