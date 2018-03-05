package com.leoyon.geom;

public class GeomFactory {

	public static Polygon parsePolygon(String wkt) throws WktException {
		Polygon ret = new Polygon();
		ret.fromText(wkt);
		return ret;
	}
	
	public static Point parsePoint(String wkt) {
		Point ret = new Point();
		ret.fromText(wkt);
		return ret;
	}
}
