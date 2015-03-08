package com.answers.musicstore.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.answers.musicstore.dao.impl.SongsDAOImpl;
import com.answers.musicstore.domain.Song;

public class SongValidator implements Validator {
	static Logger log = Logger.getLogger(SongValidator.class.getName());
	@Override
	public boolean supports(Class cls) {
		// TODO Auto-generated method stub
		return Song.class.equals(cls);
	}

	@Override
	public void validate(Object object, Errors errors) {
		if(log.isDebugEnabled()){
			log.debug(SongValidator.class.getName()+" | validate | Validation Start");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "songName", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "artistName", "artist.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "albumName", "album.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "quantity.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unitPrice", "unitprice.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "genre.required");
		Song song= (Song)object;
		if(song.getQuantity()<1){
			errors.rejectValue("quantity","quantity.more", new Object[]{"'quantity'"},"Quantity must be Positive");
		}
		try{
			Long.valueOf(song.getQuantity());
		}catch(NumberFormatException e){
			errors.rejectValue("quantity","quantity.valid", new Object[]{"'quantity'"},"Please enter valid data");
		}
		try{
			Double.valueOf(song.getUnitPrice());
			if(song.getUnitPrice() > 0 && (!song.getUnitPrice().toString().matches("[0-9]+(\\.[0-9][0-9]?)?"))){
				errors.rejectValue("unitPrice","unitPrice.valid.decimal", new Object[]{"'unitPrice'"},"Please enter valid US price");
			}
			if(song.getUnitPrice() <=0)
			{
				errors.rejectValue("unitPrice","unitPrice.valid", new Object[]{"'unitPrice'"},"Please enter valid US price");
			}
		}catch(Exception e){
			errors.rejectValue("unitPrice","unitPrice.valid", new Object[]{"'unitPrice'"},"Please enter valid US price");
		}
		
		if(log.isDebugEnabled()){
			log.debug(SongValidator.class.getName()+" | validate | Validation End");
		}
	}

}
