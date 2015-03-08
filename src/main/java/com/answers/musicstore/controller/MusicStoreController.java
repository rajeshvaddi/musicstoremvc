package com.answers.musicstore.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.answers.musicstore.domain.SearchCriteria;
import com.answers.musicstore.domain.Song;
import com.answers.musicstore.service.IGenreDropdownLookupService;
import com.answers.musicstore.service.ISongsService;
import com.answers.musicstore.utilities.MusicStoreConstants;
import com.answers.musicstore.validator.SongValidator;

@Controller
public class MusicStoreController {
	static Logger log = Logger.getLogger(MusicStoreController.class.getName());	
	private ISongsService iSongsService;
	private IGenreDropdownLookupService iGenreDropdownLookupService;
	 // @InitBinder("newSong")
		@InitBinder("newSong")
	    private void initBinderNewSong(WebDataBinder binder) {
	        binder.setValidator(new SongValidator());
	    }
		@InitBinder("editableSong")
	    private void initBinderEditableSong(WebDataBinder binder) {
	        binder.setValidator(new SongValidator());
	    }
	@RequestMapping("/")
	public String musicStore( Model model){
		if(log.isDebugEnabled()){
			log.debug(MusicStoreController.class.getName()+" | musicStore | Preparing home page");
		}
		model.addAttribute("Message","Welcome to Music Store");
		if(!model.containsAttribute("searchCriteria")){
			model.addAttribute("searchCriteria",new SearchCriteria());
		}
		if(!model.containsAttribute("songList")){
			model.addAttribute("songList",iSongsService.getAllSongs());
		}
		if(!model.containsAttribute("dropDownList")){
			model.addAttribute("dropDownList",iGenreDropdownLookupService.getLookupData());
		}
		log.info(MusicStoreController.class.getName()+" | musicStore | Done preparing home page");
		return "index";
	}
	
	@RequestMapping("/home")
	public String refreshHome( Model model){
		if(log.isDebugEnabled()){
			log.debug(MusicStoreController.class.getName()+" | refreshHome | refreshing home page");
		}
		model.addAttribute("Message","Welcome to Music Store");
		if(!model.containsAttribute("searchCriteria")){
			model.addAttribute("searchCriteria",new SearchCriteria());
		}
		if(!model.containsAttribute("songList")){
			model.addAttribute("songList",iSongsService.getAllSongs());
		}
		if(!model.containsAttribute("dropDownList")){
			model.addAttribute("dropDownList",iGenreDropdownLookupService.getLookupData());
		}
		log.info(MusicStoreController.class.getName()+" | refreshHome | Done refreshing home page");
		return "index";
	}
	@RequestMapping("/add")
	public String addNewSong(Model model){
		if(log.isDebugEnabled()){
			log.debug(MusicStoreController.class.getName()+" | addNewSong | Preparing add new song page");
		}
		model.addAttribute("addNewSongMessage","Add New Song");
		model.addAttribute("newSong", new Song());
		model.addAttribute("dropDownList",iGenreDropdownLookupService.getLookupData());
		log.info(MusicStoreController.class.getName()+" | addNewSong | Done preparing add new song page");
		return "addNewSong";
	}
	@RequestMapping(MusicStoreConstants.MAIN_CONTROLLER_SAVESONG)
	public String saveMusic(@Validated @ModelAttribute("newSong") Song song, BindingResult bindingResult, Model model){
		if(log.isDebugEnabled()){
			log.debug(MusicStoreController.class.getName()+" | saveMusic | Starting to Validate input");
		}
		if(bindingResult.hasErrors()){
			log.info(MusicStoreController.class.getName()+" | saveMusic | Validation resulted in errors");
			model.addAttribute("addNewSongMessage","Add New Song");
			model.addAttribute("newSong",song);
			model.addAttribute("dropDownList",iGenreDropdownLookupService.getLookupData());
			return "addNewSong";
		}else{
			log.info(MusicStoreController.class.getName()+" | saveMusic | calling SongService to save song");
			iSongsService.saveOrUpdateSong(song,"addSong");
			return "redirect:/";
		}
	}
	
	@RequestMapping("/editedSongSave")
	public String updateSong(@Validated @ModelAttribute("editableSong") Song song, BindingResult bindingResult, Model model){
		if(log.isDebugEnabled()){
			log.debug(MusicStoreController.class.getName()+" | updateSong | Starting to Validate input");
		}
		if(bindingResult.hasErrors()){
			log.info(MusicStoreController.class.getName()+" | updateSong | Validation resulted in errors");
			model.addAttribute("editSong","Edit Song");
			model.addAttribute("editableSong",song);
			model.addAttribute("dropDownList",iGenreDropdownLookupService.getLookupData());
			return "editSong";
		}else{
			log.info(MusicStoreController.class.getName()+" | saveMusic | calling SongService to update song");
			iSongsService.saveOrUpdateSong(song,"updateSong");
			return "redirect:/";
		}
	}
	
	@RequestMapping("/edit")
	public String editSong(@RequestParam("id") String id, Model model){
		if(log.isDebugEnabled()){
			log.debug(MusicStoreController.class.getName()+" | editSong | Preparing edit song page");
		}
		model.addAttribute("editSong","Edit Song");
		model.addAttribute("editableSong",iSongsService.getSong(id));
		model.addAttribute("dropDownList",iGenreDropdownLookupService.getLookupData());
		return "editSong";
	}
	
	@RequestMapping("/delete")
	public String deleteSong(@RequestParam("id") String id, Model model){
		if(log.isDebugEnabled()){
			log.debug(MusicStoreController.class.getName()+" | deleteSong | Deleting Song");
		}
		log.info(MusicStoreController.class.getName()+" | deleteSong | calling SongService to update song");
		iSongsService.deleteSong(id);
		return "redirect:/";
	}
	public MusicStoreController(ISongsService iSongsService, IGenreDropdownLookupService iGenreDropdownLookupService){
		this.iSongsService=iSongsService;
		this.iGenreDropdownLookupService=iGenreDropdownLookupService;
	}
}
