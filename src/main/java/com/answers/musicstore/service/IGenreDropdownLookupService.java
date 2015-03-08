package com.answers.musicstore.service;

import java.util.Collection;

import com.answers.musicstore.domain.GenreDropdownLookup;

public interface IGenreDropdownLookupService {
	
	public Collection<GenreDropdownLookup> getLookupData();
}
