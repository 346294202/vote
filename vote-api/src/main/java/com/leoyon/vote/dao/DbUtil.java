package com.leoyon.vote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leoyon.geom.Geometry;
import com.leoyon.geom.GeometryCollection;
import com.leoyon.geom.WktSerializable;

@Component
public class DbUtil {

	@Autowired
	private DataSource db;
	
	public Long insert(String table, Map<String, Object> params) throws Exception {
		
		Vector<String> fieldNames = new Vector<>();
		Vector<String> fieldValues = new Vector<>();
		for(Map.Entry<String, Object> p:params.entrySet()) {
			fieldNames.add(String.format("`%s`", p.getKey()));
			fieldValues.add("?");
		}
		
		Connection conn = null;
		try {
			conn = db.getConnection();
			PreparedStatement stat = conn.prepareStatement(
					String.format("INSERT INTO %s(%s) VALUES(%s);", 
							table, String.join(",", fieldNames), String.join(",", fieldValues)), 
					PreparedStatement.RETURN_GENERATED_KEYS);
			int i = 1;
			for(Map.Entry<String, Object> p:params.entrySet()) {
				stat.setObject(i++, p.getValue());
			}
			if(stat.executeUpdate()>0) {
				try (ResultSet rs = stat.getGeneratedKeys()) {
			        if (rs.next()) {
			        	return rs.getLong(1);
			        }
			        rs.close();
			    }
			}
		} finally {
			
			if(conn != null)
				conn.close();
		}
		//失败
		return 0L;
	}

	public void insert(String table, List<Map<String, Object>> list) throws Exception {
		
		if(list.isEmpty())
			return;
		
		Vector<String> fieldNames = new Vector<>();
		
		for(Map.Entry<String, Object> p:list.get(0).entrySet()) {
			fieldNames.add(String.format("`%s`", p.getKey()));
		}
		
		Vector<String> fieldValuesList = new Vector<>();
		for(Map<String, Object> i:list) {
			Vector<String> fieldValues = new Vector<>();
			
			for(Map.Entry<String, Object> p:i.entrySet()) {
				fieldValues.add(translateName(p.getValue()));
			}
			
			fieldValuesList.add("("+String.join(",", fieldValues)+")");
		}
		
		Connection conn = null;
		try {
			conn = db.getConnection();
			PreparedStatement stat = conn.prepareStatement(
					String.format("INSERT INTO %s(%s) VALUES %s;", 
							table, String.join(",", fieldNames), String.join(",", fieldValuesList)));
			int i = 1;
			for(Map<String, Object> map:list) {				
				for(Map.Entry<String, Object> p:map.entrySet()) {
					stat.setObject(i++, translateValue(p.getValue()));
				}
			}
			
			stat.execute();
		} finally {
			
			if(conn != null)
				conn.close();
		}
	}
	
	private String translateName(Object value) {
		if(value instanceof Geometry)
			return "ST_GeomFromText(?)";
		if(value instanceof GeometryCollection)
			return "ST_GeomCollFromText(?)";
		return "?";
	}

	private Object translateValue(Object value) {
		if(value instanceof WktSerializable)
			return ((WktSerializable)value).asText();
		return value;
	}

	/**
	 * 
	 * @Title: clear 
	 * @Description: 清空指定表
	 * @param tables
	 * @return: void
	 * @throws SQLException 
	 */
	public void clear(String[] tables) throws Exception {		
		for(String i:tables) {
			clear(i);
		}
	}
	
	/**
	 * 
	 * @Title: clear 
	 * @Description: 清空指定表
	 * @param table
	 * @return: void
	 * @throws SQLException 
	 */
	public void clear(String table) throws Exception {

		Connection conn = null;
		try {
			conn = db.getConnection();
			PreparedStatement stat = conn.prepareStatement(String.format("delete from %s;", table));
			stat.execute();
		} finally {
			
			if(conn != null)
				conn.close();
		}
	}

	public List<Map<String, Object>> select(String sql) throws Exception {
		Connection conn = null;
		try {
			conn = db.getConnection();
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet r = stat.executeQuery();
			Vector<Map<String, Object>> ret = new Vector<>();
			
			ResultSetMetaData meta = r.getMetaData();
			while(r.next()) {
				Map<String, Object> row = new HashMap<>();
				for(int i = 1;i<=meta.getColumnCount();++i) {
					String name = meta.getColumnName(i);
					row.put(name, r.getObject(name));
				}
				ret.add(row);
			}
			return ret;
		} finally {
			
			if(conn != null)
				conn.close();
		}
	}

}

