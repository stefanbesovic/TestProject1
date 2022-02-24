package com.practice.test1.domen;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@ToString
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

	public VideoOrder(Playlist playlist, Video video, int position) {
		this.playlist = playlist;
		this.video = video;
		this.position = position;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof VideoOrder)) return false;
		VideoOrder that = (VideoOrder) o;
		return Objects.equals(videoOrderId, that.videoOrderId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(videoOrderId);
	}
}
