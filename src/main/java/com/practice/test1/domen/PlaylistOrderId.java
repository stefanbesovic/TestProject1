package com.practice.test1.domen;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class PlaylistOrderId implements Serializable{
	
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
		if (!(obj instanceof PlaylistOrderId))
			return false;
		PlaylistOrderId other = (PlaylistOrderId) obj;
		return channelId == other.channelId && playlistId == other.playlistId;
	}
}
