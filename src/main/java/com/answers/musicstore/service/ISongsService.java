package com.answers.musicstore.service;

import java.util.Collection;

import com.answers.musicstore.domain.SearchCriteria;
import com.answers.musicstore.domain.Song;

public interface ISongsService {
	
	public void saveOrUpdateSong(Song song, String source) ;
	public Collection<Song> getAllSongs();
	public void deleteSong(String id);
	public Collection <Song> searchSong(SearchCriteria searchCriteria);
	public Song getSong(String id);
}
