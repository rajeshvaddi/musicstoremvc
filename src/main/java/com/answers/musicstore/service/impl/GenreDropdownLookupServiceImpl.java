package com.answers.musicstore.service.impl;

import java.util.Collection;

import org.apache.log4j.Logger;

import com.answers.musicstore.dao.IGenreDropdownLookupDAO;
import com.answers.musicstore.domain.GenreDropdownLookup;
import com.answers.musicstore.service.IGenreDropdownLookupService;

public class GenreDropdownLookupServiceImpl implements IGenreDropdownLookupService {
	static Logger log = Logger.getLogger(GenreDropdownLookupServiceImpl.class.getName());
	private IGenreDropdownLookupDAO iGenreDropdownLookupDAO;
	@Override
	public Collection<GenreDropdownLookup> getLookupData() {
		
		return iGenreDropdownLookupDAO.getDropdownList();
	}
	public GenreDropdownLookupServiceImpl(IGenreDropdownLookupDAO iGenreDropdownLookupDAO){
		if(log.isDebugEnabled()){
			log.debug(GenreDropdownLookupServiceImpl.class.getName()+" | GenreDropdownLookupServiceImpl | Gettign Loookup Data");
		}
		this.iGenreDropdownLookupDAO=iGenreDropdownLookupDAO;
	}

}
