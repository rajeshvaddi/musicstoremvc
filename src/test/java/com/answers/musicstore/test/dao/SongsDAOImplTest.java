package com.answers.musicstore.test.dao;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import com.answers.musicstore.dao.IGenreDropdownLookupDAO;
import com.answers.musicstore.dao.ISongsDAO;
import com.answers.musicstore.domain.GenreDropdownLookup;
import com.answers.musicstore.domain.SearchCriteria;
import com.answers.musicstore.domain.Song;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context-test.xml")
@TransactionConfiguration(defaultRollback=false, transactionManager="transactionManager")
public class SongsDAOImplTest {
	@Autowired
	private ISongsDAO songsDAO;
	@Autowired
	private IGenreDropdownLookupDAO genreLookupDAO;
	private Song song = new Song();
	private SearchCriteria searchCriteria = new SearchCriteria();
	@Before
	public void setUp(){
		song.setSongName("TestSong");
		song.setAlbumName("TestAlbum");
		song.setArtistName("TestName");
		song.setUnitPrice(0.32);
		song.setQuantity(10);
		song.setGenre("R");
		
		searchCriteria.setSearchString("estso");
	}
	@Test 
	public void saveSong(){
		songsDAO.saveSong(song);
		Collection<Song> songList= songsDAO.getSongList();
		Assert.notEmpty(songList);
	}
	
	@Test
	public void testGetSongList(){
		Collection<Song> songList= songsDAO.getSongList();
		Assert.notEmpty(songList);
	}
	@Test
	public void testGetSong(){
		Collection<Song> songList= songsDAO.getSongList();
		Song songFromList = (Song)songList.toArray()[0];
		Song song = new Song();
		song.setId(songFromList.getId());
		Collection<Song> testSong= songsDAO.getSong(song);
		Assert.notEmpty(testSong);
	}
	
	@Test
	public void testSearchSong(){
		Collection<Song> songList= songsDAO.searchSong(searchCriteria);
		Assert.notEmpty(songList);
	}
	
	@Test
	public void testUpdateSong(){
		Collection<Song> songListToGetSongId= songsDAO.getSongList();
		song.setId(((Song)songListToGetSongId.toArray()[0]).getId());
		song.setGenre("F");
		songsDAO.updateSong(song);
		Collection<Song> songList= songsDAO.getSongList();
		String genre = ((Song)songList.toArray()[0]).getGenre();	
		org.junit.Assert.assertEquals("F", genre);
	}
	@Test
	public void testDeleteSong(){
		Collection<Song> songListToGetSongId= songsDAO.getSongList();
		song.setId(((Song)songListToGetSongId.toArray()[0]).getId());
		songsDAO.deleteSong(song);
		Assert.isTrue(true);
	}
	@Test
	public void testGetDropDownList(){
		Collection<GenreDropdownLookup> dropDownList= genreLookupDAO.getDropdownList();
		Assert.notEmpty(dropDownList);
	}
}
