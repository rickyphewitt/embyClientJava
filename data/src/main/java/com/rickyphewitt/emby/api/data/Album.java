package com.rickyphewitt.emby.api.data;

import java.util.List;

public class Album extends MediaItem {

	int productionYear;
	List<Item> albumArtists;


	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public List<Item> getAlbumArtists() {
		return albumArtists;
	}

	public void setAlbumArtists(List<Item> albumArtists) {
		this.albumArtists = albumArtists;
	}
}
