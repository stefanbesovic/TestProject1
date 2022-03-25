package com.practice.test1.services.implementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.practice.test1.entities.Category;
import com.practice.test1.entities.Playlist;
import com.practice.test1.entities.User;
import com.practice.test1.repositories.PlaylistRepository;
import com.practice.test1.services.CategoryService;
import com.practice.test1.services.PlaylistService;
import com.practice.test1.services.UserService;

@RequiredArgsConstructor
@Service
@Slf4j
public class PlaylistServiceImplementation implements PlaylistService {
	
	private final PlaylistRepository playlistRepository;
	private final CategoryService categoryService;
	private final UserService userService;

	@Override
	public Playlist savePlaylist(Playlist playlist) {
		if(!playlistRepository.existsById(playlist.getId())) {
			return playlistRepository.save(playlist);
		}else {
			throw new DuplicateKeyException("Could not save playlist. Playlist with same ID already exists.");
		}
	}

	@Override
	public Playlist getPlaylistById(Long id) {
		return playlistRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("Playlist not found: %d", id)));
	}

	@Override
	public List<Playlist> getAllPlaylists() {
		return playlistRepository.findAll();
	}

	@Override
	public Playlist updatePlaylist(Playlist playlist, Long id) {
		Playlist existing = getPlaylistById(id);
		existing.setName(playlist.getName());

		playlistRepository.save(existing);

		return existing;
	}

	@Override
	public void deletePlaylist(Long id) {
		Playlist playlistById = getPlaylistById(id);
		playlistRepository.deleteById(playlistById.getId());
	}
	
	@Override
	public Playlist addCategory(Long playlistId, Long categoryId) {
		log.debug("Adding category {} to playlist {}.", categoryId, playlistId);

		Playlist playlist = getPlaylistById(playlistId);
		Category category = categoryService.getCategoryById(categoryId);

		if(!playlist.getCategories().contains(category)) {
			playlist.addCategory(category);
		}else {
			log.debug("Can't add category {} because it doesn't exist.", categoryId);
			return playlist;
		}

		log.info("Category {} added to playlist {}.", categoryId, playlistId);

		return playlistRepository.save(playlist);
	}
	
	@Override
	public void RemoveCategory(Long playlistId, Long categoryId) {
		log.debug("Removing category {} from playlist {}.", categoryId, playlistId);

		Playlist playlist = getPlaylistById(playlistId);
		Category category = categoryService.getCategoryById(categoryId);

		if(playlist.getCategories().contains(category)) {
			playlist.removeCategory(category);

			log.info("Category {} removed from playlist {}.", categoryId, playlistId);

			playlistRepository.save(playlist);	
		}
	}
	
	@Override
	public Set<Playlist> addPlaylistToUser(Long playlistId, Long userId) {
		log.debug("Adding playlist {} to user {}.", playlistId, userId);

		User user = userService.getUserById(userId);
		Playlist playlist = getPlaylistById(playlistId);
		playlist.setUser(user);

		playlistRepository.save(playlist);

		log.info("Playlist {} added to user {}.", playlistId, userId);

		return user.getPlaylists();
	}

	@Override
	public Playlist removePlaylistFromUser(Long playlistId) {
		log.debug("Removing playlist {} from user.", playlistId);

		Playlist playlist = getPlaylistById(playlistId);
		playlist.setUser(null);

		playlistRepository.save(playlist);

		log.info("Playlist {} removed from user.", playlistId);

		return playlist;
	}
}
