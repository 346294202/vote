package com.leoyon.vote.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
	
	@SafeVarargs
	public static List<Map<String, Object>> mapList(
			List<String> fields,
			List<Object> ... valuesList
			) {
		Vector<Map<String, Object>> ret = new Vector<>();
		
		int rows = valuesList[0].size();
		for(int row = 0; row < rows; ++row) {
			HashMap<String, Object> map = new HashMap<>();
			for(int col=0; col < fields.size(); ++col) {
				map.put(fields.get(col), valuesList[col].get(row));
			}
			ret.add(map);
		}
		
		return ret;
	}
}
