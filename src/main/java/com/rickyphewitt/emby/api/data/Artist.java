package com.rickyphewitt.emby.api.data;

public class Artist extends Item {

	private String type;
	private boolean isFolder;
	private Integer runTimeTicks;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean getIsFolder() {
		return isFolder;
	}
	public void setIsFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}
	
	public Integer getRunTimeTicks() {
		return runTimeTicks;
	}
	public void setRunTimeTicks(Integer runTimeTicks) {
		this.runTimeTicks = runTimeTicks;
	}
	
}