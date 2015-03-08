package com.answers.musicstore.domain;

import java.io.Serializable;

public class Song implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String songName;
	private String artistName;
	private String albumName;
	private long quantity;
	private Double unitPrice;
	private String genre;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}	
	
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		if(songName!=null)
		{
			stringBuffer.append("Song Name:"+songName);
		}
		if(albumName!=null)
		{
			stringBuffer.append("; Album Name:"+albumName);
		}
		if(artistName!=null){
			stringBuffer.append("; Artist Name:"+artistName);
		}
			stringBuffer.append("; Quantity:"+quantity);
		if(unitPrice!=null){
			stringBuffer.append("; Unit Price:"+unitPrice);
		}
		return stringBuffer.toString();
	}
	
}
