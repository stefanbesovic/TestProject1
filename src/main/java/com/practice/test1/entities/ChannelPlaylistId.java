package com.practice.test1.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ChannelPlaylistId implements Serializable{
	
	private long playlistId;

	private long channelId;
	
	@Override
	public int hashCode() {
		return Objects.hash(channelId, playlistId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ChannelPlaylistId))
			return false;
		ChannelPlaylistId other = (ChannelPlaylistId) obj;
		return channelId == other.channelId && playlistId == other.playlistId;
	}
}
