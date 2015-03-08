package com.answers.musicstore.dao.impl;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTransactionManager;

import com.answers.musicstore.dao.IGenreDropdownLookupDAO;
import com.answers.musicstore.domain.GenreDropdownLookup;
import com.answers.musicstore.domain.Song;
import com.answers.musicstore.service.impl.GenreDropdownLookupServiceImpl;

public class GenreDropdownLookupDAO implements IGenreDropdownLookupDAO {
	static Logger log = Logger.getLogger(GenreDropdownLookupDAO.class.getName());
	private HibernateTransactionManager transactionManager;
	
	public GenreDropdownLookupDAO(HibernateTransactionManager transactionManager){
		this.transactionManager=transactionManager;
	}
	@Override
	public Collection<GenreDropdownLookup> getDropdownList() {
		if(log.isDebugEnabled()){
			log.debug(GenreDropdownLookupDAO.class.getName()+" | getDropdownList | Gettign dropdown list");
		}
		Collection<GenreDropdownLookup> dropDownList=null;
		Transaction tx = null;
		Session session = transactionManager.getSessionFactory().openSession();
		try{
			tx = session.beginTransaction();
			tx.begin();
			Criteria criteria = session.createCriteria(GenreDropdownLookup.class);
			dropDownList = criteria.list();
			tx.commit();
		    tx = null;
		}catch (HibernateException e) {
		    if (tx != null)
		        tx.rollback();
		    throw e;  
		} finally {
		    session.close();
		}
		
		log.info(GenreDropdownLookupDAO.class.getName()+" | getDropdownList | retried dropdown list of size: "+dropDownList.size());
		
		return dropDownList;
	}

}
