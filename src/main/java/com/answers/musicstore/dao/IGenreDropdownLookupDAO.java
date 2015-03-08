package com.answers.musicstore.dao;

import java.util.Collection;

import com.answers.musicstore.domain.GenreDropdownLookup;

public interface IGenreDropdownLookupDAO {
	public Collection<GenreDropdownLookup> getDropdownList();
}
