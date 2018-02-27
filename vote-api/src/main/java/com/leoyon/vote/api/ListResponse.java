package com.leoyon.vote.api;

import java.util.Collection;

public class ListResponse<E> extends AbstractResponse<ListResponse<E>> {

	private Collection<E> items;
	
	public static <T> ListResponse<T> success(Collection<T> items) {
		return new ListResponse<>(1, "", items);
	}

	public ListResponse(int code, String error, Collection<E> items) {
		super(code, error);
		this.items = items;
	}

	public Collection<E> getItems() {
		return items;
	}

	@Override
	public ListResponse<E> getData() {
		return this;
	}
	
}
