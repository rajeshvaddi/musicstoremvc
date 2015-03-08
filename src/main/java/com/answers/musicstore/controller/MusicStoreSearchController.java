package com.answers.musicstore.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.answers.musicstore.dao.impl.SongsDAOImpl;
import com.answers.musicstore.domain.SearchCriteria;
import com.answers.musicstore.domain.Song;
import com.answers.musicstore.service.IGenreDropdownLookupService;
import com.answers.musicstore.service.ISongsService;
import com.answers.musicstore.service.impl.GenreDropdownLookupServiceImpl;
import com.answers.musicstore.validator.SongSearchValidator;

@Controller
@RequestMapping("search")
public class MusicStoreSearchController {
	static Logger log = Logger.getLogger(MusicStoreSearchController.class.getName());	
	private ISongsService isongsService;
	private IGenreDropdownLookupService iGenreDropdownLookupService;
	 @InitBinder("searchCriteria")
	    private void initBinder(WebDataBinder binder) {
	        binder.setValidator(new SongSearchValidator());
	    }
	 @RequestMapping("search")
	 public String searchMusic(@Validated @ModelAttribute("searchCriteria") SearchCriteria searchCriteria, BindingResult bindingResult, Model model){
			if(log.isDebugEnabled()){
				log.debug(MusicStoreSearchController.class.getName()+" | searchMusic | Start");
			}
		 Collection<Song> searchResult =null;
			if(bindingResult.hasErrors()){
				log.info(MusicStoreSearchController.class.getName()+" | searchMusic | Validation resulted in errors");
				model.addAttribute("Message","Welcome to Music Store");
				model.addAttribute("searchCriteria",searchCriteria);
				return "index";
			}else{
				searchResult= isongsService.searchSong(searchCriteria);
				if(searchResult.size()==0){
					log.info(MusicStoreSearchController.class.getName()+" | searchMusic | Search did not yield any results");
					model.addAttribute("Message","Welcome to Music Store");
					model.addAttribute("searchResultMessage","Did not find any results");
				}else{
					log.info(MusicStoreSearchController.class.getName()+" | searchMusic | Search got "+searchResult.size()+" songs");
					model.addAttribute("searchCriteria",new SearchCriteria());
					model.addAttribute("Message","Welcome to Music Store");
					model.addAttribute("songList",searchResult);
					model.addAttribute("dropDownList",iGenreDropdownLookupService.getLookupData());
				}
			return "index";
			}
		}
	 public MusicStoreSearchController(ISongsService iSongsService, IGenreDropdownLookupService iGenreDropdownLookupService){
		 this.isongsService=iSongsService;
		 this.iGenreDropdownLookupService=iGenreDropdownLookupService;
	 }
}
