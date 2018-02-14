package com.leoyon.vote.util;

import java.util.HashMap;
import java.util.Map;

public class M<K, V> {
	
	public static M<String, Object> map() {
		return new M<>();
	}

	private HashMap<K, V> peer = new HashMap<>();
	
	public M<K, V> put(K key, V value) {
		peer.put(key, value);
		return this;
	}
	
	public Map<K, V> build() {
		return peer;
	}
}
