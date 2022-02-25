package com.practice.test1.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class PlaylistOrder {
	
	@EmbeddedId
	private PlaylistOrderId playlistOrderId = new PlaylistOrderId();
	 
	@ManyToOne
	@MapsId("channelId")
	@JoinColumn(name = "channel_id")
	private Channel channel;
	
	@ManyToOne
	@MapsId("playlistId")
	@JoinColumn(name = "playlist_id")
	private Playlist playlist;
	
	private int position;

	public PlaylistOrder(Channel channel, Playlist playlist, int position) {
		this.channel = channel;
		this.playlist = playlist;
		this.position = position;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlaylistOrder)) return false;
		PlaylistOrder that = (PlaylistOrder) o;
		return Objects.equals(playlistOrderId, that.playlistOrderId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(playlistOrderId);
	}
}
