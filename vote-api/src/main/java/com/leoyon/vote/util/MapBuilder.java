package com.leoyon.vote.util;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K, V> {

	private HashMap<K, V> peer = new HashMap<>();
	
	public MapBuilder<K, V> put(K key, V value) {
		peer.put(key, value);
		return this;
	}
	
	public Map<K, V> build() {
		return peer;
	}
}
