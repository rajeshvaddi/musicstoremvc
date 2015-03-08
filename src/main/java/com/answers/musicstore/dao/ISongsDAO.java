package com.answers.musicstore.dao;

import java.util.Collection;

import com.answers.musicstore.domain.SearchCriteria;
import com.answers.musicstore.domain.Song;

public interface ISongsDAO {
	public Collection<Song> getSongList();
	public String saveSong(Song song);
	public Collection<Song> searchSong(SearchCriteria searchCriteria);
	public void deleteSong(Song song);
	public Collection<Song>  getSong(Song song);
	public String updateSong(Song song);
}
