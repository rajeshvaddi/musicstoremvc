package com.answers.musicstore.service.impl;

import java.util.Collection;

import org.apache.log4j.Logger;

import com.answers.musicstore.dao.ISongsDAO;
import com.answers.musicstore.domain.SearchCriteria;
import com.answers.musicstore.domain.Song;
import com.answers.musicstore.service.ISongsService;
import com.answers.musicstore.validator.SongSearchValidator;

public class SongsServiceImpl implements ISongsService {
	static Logger log = Logger.getLogger(SongsServiceImpl.class.getName());
	private ISongsDAO songsDAO =null;

	@Override
	public void saveOrUpdateSong(Song song, String source) {
		if(log.isDebugEnabled()){
			log.debug(SongsServiceImpl.class.getName()+" | saveOrUpdateSong | Start for" +source);
		}
		if("addSong"==source){
		songsDAO.saveSong(song);
		}else{
			songsDAO.updateSong(song);
		}
		if(log.isDebugEnabled()){
			log.debug(SongsServiceImpl.class.getName()+" | saveOrUpdateSong | End for" +source);
		}
	}

	@Override
	public Collection<Song> getAllSongs() {
		if(log.isDebugEnabled()){
			log.debug(SongsServiceImpl.class.getName()+" | getAllSongs | Getting all Songs");
		}
		return songsDAO.getSongList();
	}

	@Override
	public void deleteSong(String id) {
		if(log.isDebugEnabled()){
			log.debug(SongsServiceImpl.class.getName()+" | deleteSong | Deleting song with Id :"+id);
		}
		Song song = new Song();
		song.setId(id);
		songsDAO.deleteSong(song);
	}

	@Override
	public Collection<Song> searchSong(SearchCriteria searchCriteria) {
		if(log.isDebugEnabled()){
			log.debug(SongsServiceImpl.class.getName()+" | searchSong | Search criteria :"+searchCriteria.getSearchString());
		}
		return songsDAO.searchSong(searchCriteria);
	}
	
	public SongsServiceImpl(ISongsDAO ISongsDAO){
		this.songsDAO = ISongsDAO;
	}

	@Override
	public Song getSong(String id) {
		if(log.isDebugEnabled()){
			log.debug(SongsServiceImpl.class.getName()+" | getSong | Getting Song with id :"+id);
		}
		Song song = new Song();
		song.setId(id);
		Collection<Song> songRetrived = songsDAO.getSong(song);
		return (Song) songRetrived.toArray()[0];
	}

}
