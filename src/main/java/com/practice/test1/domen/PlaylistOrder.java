package com.practice.test1.domen;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Data;
import lombok.NoArgsConstructor;

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
}
