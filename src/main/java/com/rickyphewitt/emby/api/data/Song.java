package com.rickyphewitt.emby.api.data;

public class Song extends MediaItem {
	
	public String container;
	public int trackNumber;
	
	public String getContainer() {
		return container;
	}
	public void setContainer(String container) {
		this.container = container;
	}
	public int getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}
	
}