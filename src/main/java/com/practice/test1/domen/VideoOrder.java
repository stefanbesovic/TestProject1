package com.practice.test1.domen;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class VideoOrder {
	@EmbeddedId
	private VideoOrderId videoOrderId = new VideoOrderId();
	
	@ManyToOne
	@MapsId("playlistId")
	@JoinColumn(name = "playlist_id")
	private Playlist playlist;
	
	@ManyToOne
	@MapsId("videoId")
	@JoinColumn(name = "video_id")
	private Video video;
	
	private int position;
}
