package com.rickyphewitt.emby.api.data;

import java.util.List;

public class ItemSet<T> {

	private List<T> items;

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}	
}
