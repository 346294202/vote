package com.leoyon.vote.api;

import java.util.Collection;

public class ListResponseItems<E> {

	private Collection<E> items;

	ListResponseItems(Collection<E> items) {
		super();
		this.items = items;
	}

	public Collection<E> getItems() {
		return items;
	}

	public void setItems(Collection<E> items) {
		this.items = items;
	}
	
	
}
