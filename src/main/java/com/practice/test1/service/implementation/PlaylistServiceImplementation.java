package com.practice.test1.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.test1.domen.Category;
import com.practice.test1.domen.Playlist;
import com.practice.test1.repository.CategoryRepository;
import com.practice.test1.repository.PlaylistRepository;
import com.practice.test1.service.PlaylistService;

@Service
public class PlaylistServiceImplementation implements PlaylistService {
	
	private PlaylistRepository playlistRepository;
	private CategoryRepository categoryRepository;
	
	public PlaylistServiceImplementation(PlaylistRepository playlistRepository, CategoryRepository categoryRepository) {
		super();
		this.playlistRepository = playlistRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public Playlist savePlaylist(Playlist playlist) {
		if(!playlistRepository.existsById(playlist.getId())) {
			return playlistRepository.save(playlist);
		}else {
			return null;
		}
	}

	@Override
	public Playlist getPlaylistById(long id) {
		return playlistRepository.findById(id).orElse(null);
	}

	@Override
	public List<Playlist> getAllPlaylists() {
		return playlistRepository.findAll();
	}

	@Override
	public Playlist updatePlaylist(Playlist playlist, long id) {
		Playlist existing = playlistRepository.findById(id).orElse(null);
		if(existing.equals(null)) {
			return null;
		}
		existing.setName(playlist.getName());
		playlistRepository.save(existing);
		return existing;
	}

	@Override
	public void deletePlaylist(long id) {
		Playlist exists = playlistRepository.findById(id).orElse(null);
		if(exists.equals(null)) {
			return;
		}
		playlistRepository.deleteById(id);
	}
	
	@Override
	public Playlist addCategory(long playlistId, long categoryId) {
		Playlist playlist = playlistRepository.findById(playlistId).get();
		Category category = categoryRepository.findById(categoryId).get();
		if(playlist.equals(null)) {
			return null;
		}
		if(category.equals(null)) {
			return null;
		}
		if(!playlist.getCategories().contains(category)) {
			playlist.addCategory(category);
		}else {
			return playlist;
		}
		playlist.addCategory(category);
		return playlistRepository.save(playlist);
	}
	
	@Override
	public void RemoveCategory(long playlistId, long categoryId) {
		Playlist playlist = playlistRepository.findById(playlistId).get();
		Category category = categoryRepository.findById(categoryId).get();
		if(playlist.equals(null)) {
			return;
		}
		if(category.equals(null)) {
			return;
		}
		if(playlist.getCategories().contains(category)) {
			playlist.removeCategory(category);
			playlistRepository.save(playlist);	
		}else {
			return;
		}		
	}
}
