/**   
 * Copyright © 2017 dibu. All rights reserved.
 * 
 * @Title: GeoPointTools.java
 * @Prject: dibu-db
 * @Package: com.daibugroup.db.po.geom 
 * @Description: TODO
 * @author:wangjiang
 * @date: 2017年7月13日 下午4:29:43 
 */
package com.leoyon.geom;

/** 
 * @ClassName: GeoPointTools
 * @Description: 点对象
 * @author:wangjiang
 * @date: 2017年7月13日 下午4:29:43  
 */
public class Point implements WktSerializable, Geometry{

	private Double x;
	private Double y;
	public Point(double x, double y) {
		setX(x);
		setY(y);
	}
	
	/** 
	 * @Title:Point
	 * @Description:TODO  
	 */
	public Point() {
		super();
	}

	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	
	@Override
	public String asText() {
		return String.format("POINT(%s)", toString());
	}
	
	/**
	 * 接收两种格式：
	 * 1 POINT(x y)
	 * 2 x y
	 */
	@Override
	public void fromText(String wkt) {
		String s = wkt;
		if(s.startsWith("POINT")) {
			s = wkt.substring(6, wkt.length()-1);
		}
		String[] xy = s.split(" ");
		setX(Double.parseDouble(xy[0]));
		setY(Double.parseDouble(xy[1]));
	}
	
	@Override
	public String toString() {
		return String.format("%.06f %.06f", getX(), getY());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
}
