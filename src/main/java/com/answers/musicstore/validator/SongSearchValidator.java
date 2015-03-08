package com.answers.musicstore.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.answers.musicstore.dao.impl.SongsDAOImpl;
import com.answers.musicstore.domain.SearchCriteria;

public class SongSearchValidator implements Validator {
	static Logger log = Logger.getLogger(SongSearchValidator.class.getName());
	@Override
	public boolean supports(Class clazz) {
		
		return SearchCriteria.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		if(log.isDebugEnabled()){
			log.debug(SongSearchValidator.class.getName()+" | validate | Validation Start");
		}
		ValidationUtils.rejectIfEmpty(errors, "searchString", "searchstring.empty");
		if(log.isDebugEnabled()){
			log.debug(SongSearchValidator.class.getName()+" | validate | Validation End");
		}
	}

}
