package com.leoyon.vote.api;

import java.util.Collection;

public class ListResponse<E> extends AbstractResponse<ListResponseItems<E>> {

	private ListResponseItems<E> data;
	
	public static <T> ListResponse<T> success(Collection<T> items) {
		return new ListResponse<>(1, "", items);
	}

	private ListResponse(int code, String error, Collection<E> items) {
		super(code, error);
		this.data = new ListResponseItems<>(items);
	}

	@Override
	public ListResponseItems<E> getData() {
		return data;
	}

	public void setData(ListResponseItems<E> data) {
		this.data = data;
	}
	
}
