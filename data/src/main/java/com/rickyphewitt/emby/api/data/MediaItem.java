package com.rickyphewitt.emby.api.data;

import java.util.Map;

public class MediaItem extends Item {

	private String type;
	private boolean isFolder;
	private int runTimeTicks;

	private String primaryImage;
	private Map<String, String> imageTags;
	
	public boolean getIsFolder() {
		return this.isFolder;
	}
	public void setIsFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}
	
	public Integer getRunTimeTicks() {
		return this.runTimeTicks;
	}
	public void setRunTimeTicks(Integer runTimeTicks) {
		this.runTimeTicks = runTimeTicks;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, String> getImageTags() {
		return this.imageTags;
	}

	public void setImageTags(Map<String, String> imageTags) {
		this.imageTags = imageTags;
	}

	public String getPrimaryImage() {
		return this.primaryImage;
	}

	public void setPrimaryImage(String primaryImage) {
		this.primaryImage = primaryImage;
	}

}
