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
public class ChannelPlaylist {
	
	@EmbeddedId
	private ChannelPlaylistId channelPlaylistId = new ChannelPlaylistId();
	 
	@ManyToOne
	@MapsId("channelId")
	@JoinColumn(name = "channel_id")
	private Channel channel;
	
	@ManyToOne
	@MapsId("playlistId")
	@JoinColumn(name = "playlist_id")
	private Playlist playlist;
	
	private int position;

	public ChannelPlaylist(Channel channel, Playlist playlist, int position) {
		this.channel = channel;
		this.playlist = playlist;
		this.position = position;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ChannelPlaylist)) return false;
		ChannelPlaylist that = (ChannelPlaylist) o;
		return Objects.equals(channelPlaylistId, that.channelPlaylistId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(channelPlaylistId);
	}

	@Override
	public String toString() {
		return "PlaylistOrder{" +
				"channel=" + channel.getId() +
				", playlist=" + playlist.getId() +
				", position=" + position +
				'}';
	}
}
