/**   
 * Copyright © 2017 dibu. All rights reserved.
 * 
 * @Title: Polygon.java 
 * @Prject: dibu-db
 * @Package: com.daibugroup.db.po.geom 
 * @Description: TODO
 * @author:wangjiang
 * @date: 2017年7月13日 下午4:30:17 
 */
package com.leoyon.geom;

/** 
 * @ClassName: Polygon 
 * @Description: 多边形
 * @author:wangjiang
 * @date: 2017年7月13日 下午4:30:17  
 */
public class Polygon implements WktSerializable, Geometry{

	private Ring exteriorRing=new Ring();

	public Ring getExteriorRing() {
		return exteriorRing;
	}

	public void setExteriorRing(Ring exteriorRing) {
		this.exteriorRing = exteriorRing;
	}

	@Override
	public String asText() {
		return String.format("POLYGON((%s))", exteriorRing.asText());
	}

	@Override
	public void fromText(String wkt) throws WktException {
		String s = wkt.substring(9, wkt.length()-2);
		getExteriorRing().fromText(s);
	}
	
	
}
