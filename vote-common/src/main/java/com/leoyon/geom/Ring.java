/**   
 * Copyright © 2017 dibu. All rights reserved.
 * 
 * @Title: Ring.java 
 * @Prject: dibu-db
 * @Package: com.daibugroup.db.po.geom 
 * @Description: TODO
 * @author:wangjiang
 * @date: 2017年7月13日 下午4:35:17 
 */
package com.leoyon.geom;

import java.util.List;
import java.util.Vector;

/** 
 * @ClassName: Ring 
 * @Description: TODO
 * @author:wangjiang
 * @date: 2017年7月13日 下午4:35:17  
 */
public class Ring implements WktSerializable {
	
	private List<Point> points = new Vector<>();

	@Override
	public String asText() {
		StringBuilder sb = new StringBuilder();
		for(Point i:points) {
			
			if(sb.length()>0)
				sb.append(",");
			sb.append(i.toString());
		}
		return sb.toString();
	}

	/**
	 * 格式：x y,x y,x y,...
	 */
	@Override
	public void fromText(String wkt) throws WktException {
		String[] xyArray = wkt.split(",");
		points = new Vector<>();
		for(String i:xyArray) {
			String[] xy = i.split(" ");
			points.add(new Point(Double.parseDouble(xy[0]), Double.parseDouble(xy[1])));
		}
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

}
