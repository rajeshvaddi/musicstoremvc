package com.answers.musicstore.dao.impl;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTransactionManager;

import com.answers.musicstore.dao.ISongsDAO;
import com.answers.musicstore.domain.SearchCriteria;
import com.answers.musicstore.domain.Song;
import com.answers.musicstore.utilities.MusicStoreConstants;


public class SongsDAOImpl implements ISongsDAO {
	
	static Logger log = Logger.getLogger(SongsDAOImpl.class.getName());
	private HibernateTransactionManager transactionManager;
	
	public SongsDAOImpl(HibernateTransactionManager transactionManager){
		if(log.isDebugEnabled())
			log.debug("Initializing Transaction Manager for SongsDAOImpl");
		this.transactionManager=transactionManager;
	}
	@Override
	public Collection<Song> getSongList() {
		if(log.isDebugEnabled()){
			log.debug(SongsDAOImpl.class.getName()+" | getSongList | Retrieving all songs");
		}
		Collection<Song> allSongs = null;
		Transaction tx = null;
		Session session = transactionManager.getSessionFactory().openSession();
		try{
			tx = session.beginTransaction();
			tx.begin();
			Criteria criteria = session.createCriteria(Song.class);
			allSongs = criteria.list();
			tx.commit();
		    tx = null;
		}catch (HibernateException e) {
		    if (tx != null)
		        tx.rollback();
		    throw e;  
		} finally {
		    session.close();
		}
		if(log.isDebugEnabled()){
			log.debug(SongsDAOImpl.class.getName()+" | getSongList | Done retrieving all songs");
		}
		
		log.info(SongsDAOImpl.class.getName()+" | getSongList | Retrieved complete Songs list of Size :"+allSongs.size());
		return allSongs;
	}

	@Override
	public String saveSong(Song song) {
		if(log.isDebugEnabled()){
			log.debug(SongsDAOImpl.class.getName()+" | saveSong | Start Saving song");
		}
		Transaction tx = null;
		Session session = transactionManager.getSessionFactory().openSession();
		Collection <Song> checkingCollection = checkForExistance(song.getSongName(),song.getArtistName(),MusicStoreConstants.EMPTY_STRING);
		if(checkingCollection.size()==0){
			try{
				tx = session.beginTransaction();
				tx.begin();
				session.save(song);
				tx.commit();
				tx=null;
			}catch(HibernateException e){
				 if (tx != null)
					 tx.rollback();
				 throw e;  
			}finally{
				session.close();
			}
			if(log.isDebugEnabled()){
				log.debug(SongsDAOImpl.class.getName()+" | saveSong | Done saving song");
			}
			return MusicStoreConstants.ADDED_NEW_SONG;
		}else{
			if(log.isDebugEnabled()){
				log.debug(SongsDAOImpl.class.getName()+" | saveSong | Song you are trying to saved already exists");
			}
			return MusicStoreConstants.ALREADY_EXISTS;
		}
	}

	@Override
	public Collection<Song> searchSong(SearchCriteria searchCriteria) {
		if(log.isDebugEnabled()){
			log.debug(SongsDAOImpl.class.getName()+" | searchSong | Start searching song");
		}
		Collection <Song> checkingCollection = checkForExistance(searchCriteria.getSearchString(),searchCriteria.getSearchString(),MusicStoreConstants.SEARCH_ORIGIN);
		if(log.isDebugEnabled()){
			log.debug(SongsDAOImpl.class.getName()+" | searchSong | Search returned "+checkingCollection.size()+" songs");
			log.debug(SongsDAOImpl.class.getName()+" | searchSong | Returning search result");
		}
		log.info(SongsDAOImpl.class.getName()+" | searchSong | Done Searching");
		return checkingCollection;
	}
	@Override
	public void deleteSong(Song song) {
		if(log.isDebugEnabled()){
			log.debug(SongsDAOImpl.class.getName()+" | deleteSong | Start deleting song");
		}
		Transaction tx = null;
		Session session = transactionManager.getSessionFactory().openSession();
		try{
			tx = session.beginTransaction();
			tx.begin();
			session.createCriteria(Song.class);
			session.delete(song);
			tx.commit();
			tx=null;
		}catch(HibernateException e){
			if (tx != null)
			 tx.rollback();
		 throw e;  
		}finally{
			if(log.isDebugEnabled()){
				log.debug(SongsDAOImpl.class.getName()+" | deleteSong | Deletd song with Id :"+song.getId());
			}
			log.info(SongsDAOImpl.class.getName()+" | deleteSong | Done deleting");
			session.close();
		}		
	}
	@Override
	public Collection<Song> getSong(Song song) {
		if(log.isDebugEnabled()){
			log.debug(SongsDAOImpl.class.getName()+" | getSong | Start getSong");
		}
		Transaction tx = null;
		Session session = transactionManager.getSessionFactory().openSession();
		
		Collection<Song> criteriaResult = null;
		try{
			tx = session.beginTransaction();
			tx.begin();
			Criteria criteria = session.createCriteria(Song.class);
			criteria.add(Restrictions.eq(MusicStoreConstants.ID, song.getId()));
			criteriaResult = criteria.list();
			tx.commit();
			tx=null;
		}catch(HibernateException e){
			if (tx != null)
			 tx.rollback();
		 throw e;  
		}finally{
			session.close();
		}		
		if(log.isDebugEnabled()){
			log.debug(SongsDAOImpl.class.getName()+" | getSong | Search returned "+criteriaResult.size()+" songs");
			log.debug(SongsDAOImpl.class.getName()+" | getSong | Returning song with following Id :"+song.getId());
		}
		log.info(SongsDAOImpl.class.getName()+" | getSong | Done getSong");
		return criteriaResult;
	}
	
	@Override
	public String updateSong(Song song) {
		if(log.isDebugEnabled()){
			log.debug(SongsDAOImpl.class.getName()+" | updateSong | Start updateSong");
		}
		Transaction tx = null;
		Session session = transactionManager.getSessionFactory().openSession();
		Collection <Song> checkingCollection = checkForExistance(song.getSongName(),song.getArtistName(),MusicStoreConstants.EMPTY_STRING);
		Song checkedSong = (Song)checkingCollection.toArray()[0];
		if(song.getId().equals(checkedSong.getId())){
			try{
				tx = session.beginTransaction();
				tx.begin();
				session.merge(song);
				tx.commit();
				tx=null;
			}catch(HibernateException e){
				 if (tx != null)
					 tx.rollback();
				 throw e;  
			}finally{
				session.close();
			}
			if(log.isDebugEnabled()){
				log.debug(SongsDAOImpl.class.getName()+" | updateSong | Song with id: "+song.getId()+"Updated.");
			}
			log.info(SongsDAOImpl.class.getName()+" | updateSong | Update Done");
			return MusicStoreConstants.UPDATED;
		}else{
			if(log.isDebugEnabled()){
				log.debug(SongsDAOImpl.class.getName()+" | updateSong | This song can not be updated as song with mentioned name and album already exists");
			}
			return MusicStoreConstants.ALREADY_EXISTS;
		}
	}
	
	private Collection<Song> checkForExistance(String songName, String artistName,String checkOrigin){
		if(log.isDebugEnabled()){
			log.debug(SongsDAOImpl.class.getName()+" | checkForExistance | Start checkForExistance");
		}
		Transaction tx = null;
		Session session = transactionManager.getSessionFactory().openSession();
		
		Collection<Song> criteriaResult = null;
		try{
		tx = session.beginTransaction();
		tx.begin();
		Criteria criteria = session.createCriteria(Song.class);
		if(log.isDebugEnabled()){
			log.debug(SongsDAOImpl.class.getName()+" | checkForExistance | request originated from :"+checkOrigin);
		}
		if(MusicStoreConstants.SEARCH_ORIGIN.equals(checkOrigin)){
			if(log.isDebugEnabled()){
				log.debug(SongsDAOImpl.class.getName()+" | checkForExistance | now we are searching using like in sql");
			}
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.ilike(MusicStoreConstants.SONG_NAME, songName,MatchMode.ANYWHERE));
			or.add(Restrictions.ilike(MusicStoreConstants.ARTIST_NAME, artistName,MatchMode.ANYWHERE));
			criteria.add(or);
		}else{
			if(log.isDebugEnabled()){
				log.debug(SongsDAOImpl.class.getName()+" | checkForExistance | now we are searching using = in sql");
			}
			criteria.add(Restrictions.ilike(MusicStoreConstants.SONG_NAME, songName));
			criteria.add(Restrictions.ilike(MusicStoreConstants.ARTIST_NAME, artistName));
		}
		criteriaResult = criteria.list();
		tx.commit();
		tx=null;
		}catch(HibernateException e){
			if (tx != null)
			 tx.rollback();
		 throw e;  
		}finally{
			session.close();
		}		
		if(log.isDebugEnabled()){
			log.debug(SongsDAOImpl.class.getName()+" | checkForExistance | checkForExistance returned result of size :"+criteriaResult.size());
		}
		log.info(SongsDAOImpl.class.getName()+" | checkForExistance | Done checkForExistance ");
		return criteriaResult;
	}
}
