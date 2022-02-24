package com.practice.test1.domen;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class VideoOrderId implements Serializable{
	private long playlistId;
	private long videoId;
	
	@Override
	public int hashCode() {
		return Objects.hash(playlistId, videoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof VideoOrderId))
			return false;
		VideoOrderId other = (VideoOrderId) obj;
		return playlistId == other.playlistId && videoId == other.videoId;
	}
}
