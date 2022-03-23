package com.practice.test1.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.*;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistVideo {

	@EmbeddedId
	private PlaylistVideoId playlistVideoId = new PlaylistVideoId();
	 
	@ManyToOne
	@MapsId("playlistId")
	@JoinColumn(name = "playlist_id")
	private Playlist playlist;
	
	@ManyToOne
	@MapsId("videoId")
	@JoinColumn(name = "video_id")
	private Video video;
	
	private int position;

	public PlaylistVideo(Playlist playlist, Video video, int position) {
		this.playlist = playlist;
		this.video = video;
		this.position = position;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlaylistVideo)) return false;
		PlaylistVideo that = (PlaylistVideo) o;
		return Objects.equals(playlistVideoId, that.playlistVideoId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(playlistVideoId);
	}

	@Override
	public String toString() {
		return "VideoOrder{" +
				"playlist=" + playlist.getId() +
				", video=" + video.getId() +
				", position=" + position +
				'}';
	}
}
