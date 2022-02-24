package com.practice.test1.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.practice.test1.domen.Category;
import com.practice.test1.domen.Playlist;
import com.practice.test1.domen.User;
import com.practice.test1.repository.PlaylistRepository;
import com.practice.test1.service.CategoryService;
import com.practice.test1.service.PlaylistService;
import com.practice.test1.service.UserService;

@Service
public class PlaylistServiceImplementation implements PlaylistService {
	
	private final PlaylistRepository playlistRepository;
	private final CategoryService categoryService;
	private final UserService userService;
	
	public PlaylistServiceImplementation(PlaylistRepository playlistRepository, CategoryService categoryRepository, UserService userService) {
		super();
		this.playlistRepository = playlistRepository;
		this.categoryService = categoryRepository;
		this.userService = userService;
	}
	
	@Override
	public Playlist savePlaylist(Playlist playlist) {
		if(!playlistRepository.existsById(playlist.getId())) {
			return playlistRepository.save(playlist);
		}else {
			throw new DuplicateKeyException(String.format("Could not save playlist. Playlist with same ID already exists."));
		}
	}

	@Override
	public Playlist getPlaylistById(long id) {
		return playlistRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not get. Playlist not found: %d", id)));
	}

	@Override
	public List<Playlist> getAllPlaylists() {
		return playlistRepository.findAll();
	}

	@Override
	public Playlist updatePlaylist(Playlist playlist, long id) {
		Playlist existing = playlistRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not update. Playlist not found: %d", id)));
		existing.setName(playlist.getName());
		playlistRepository.save(existing);
		return existing;
	}

	@Override
	public void deletePlaylist(long id) {
		playlistRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not delete. Playlist not found: %d", id)));
		playlistRepository.deleteById(id);
	}
	
	@Override
	public Playlist addCategory(long playlistId, long categoryId) {
		Playlist playlist = getPlaylistById(playlistId);
		Category category = categoryService.getCategoryById(categoryId);
		if(!playlist.getCategories().contains(category)) {
			playlist.addCategory(category);
		}else {
			return playlist;
		}
		return playlistRepository.save(playlist);
	}
	
	@Override
	public void RemoveCategory(long playlistId, long categoryId) {
		Playlist playlist = getPlaylistById(playlistId);
		Category category = categoryService.getCategoryById(categoryId);
		if(playlist.getCategories().contains(category)) {
			playlist.removeCategory(category);
			playlistRepository.save(playlist);	
		}else {
			return;
		}		
	}
	
	@Override
	public Set<Playlist> addPlaylistToUser(long playlistId, long userId) {
		User user = userService.getUserById(userId);
		Playlist playlist = getPlaylistById(playlistId);
		playlist.setUser(user);
		playlistRepository.save(playlist);
		return user.getPlaylists();
	}

	@Override
	public Playlist removePlaylistFromUser(long playlistId) {
		Playlist playlist = getPlaylistById(playlistId);
		playlist.setUser(null);
		playlistRepository.save(playlist);
		return playlist;
	}
}
