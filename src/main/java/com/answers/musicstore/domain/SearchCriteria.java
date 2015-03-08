package com.answers.musicstore.domain;

import java.io.Serializable;

public class SearchCriteria implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchString;

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	public String toString(){
		if(null!=searchString){
			return "Search Criteria :"+searchString;
		}
		return null;
	}

}
