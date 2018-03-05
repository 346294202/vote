package com.leoyon.geom;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


/**
 * 
 * @ClassName: GeomUtil 
 * @Description: 空间计算相关函数
 * @author:wangjiang
 * @date: 2017年2月14日 下午2:18:52
 */
public class GeomUtil {

	/**
	 * 
	 * @Title: buildMercatorPoint 
	 * @Description: 将经纬度转换成米
	 * @param lat    纬度,(90>lat>-90) 
	 * @param lng    经度,(180>lng>-180) 
	 * @return
	 * @return: GeoPointTools
	 */
	public static Point buildMercatorPoint(double lat,double lng){
		if(lat > 90 || lat < -90)
			return null;
		if(lng > 180 || lng < -180)
			return null;
		
		double x = lng * 111319.49079327357;
		double a = lat * 0.017453292519943295;
		double y = 3189068.5 * Math.log((1.0 + Math.sin(a)) / (1.0 - Math.sin(a))); 
		return new Point(x, y);
	}

	/**
	 * 
	 * @Title: getDistance 
	 * @Description: 计算两点间距离
	 * @param pt1  经纬度
	 * @param pt2  经纬度
	 * @return
	 * @return: Double 米
	 */
	public static Double getMecartoDistance(Point pt1, Point pt2) {
		if(pt1.equals(pt2))
			return .0;
		double Lat1 = pt1.getY();
		double Lon1 = pt1.getX();
		double Lat2 = pt2.getY();
		double Lon2 = pt2.getX();
		
		double PI = Math.PI;
		Lat1 = Lat1 * PI / 180.0;
		Lon1 = Lon1 * PI / 180.0;
		Lat2 = Lat2 * PI / 180.0;
		Lon2 = Lon2 * PI / 180.0;
		double k = Math.cos( Lat1 ) * Math.cos( Lat2 ) * Math.cos( Lon2 - Lon1 ) + Math.sin( Lat1 ) * Math.sin( Lat2 );
		if(k>1)
			k=1;
		return 6378137 * Math.acos(k); 
	}

	public static Double getMecartoDistance(List<Double[]> points) {
		Double sum = .0;
		Double[] lastPt = null;
		for(Double[] i:points) {
			if(lastPt == null)
				lastPt = i;
			else
				sum += getMecartoDistance(new Point(lastPt[0], lastPt[0]), new Point(i[0], i[1]));
			lastPt = i;
		}
		return sum;
	}
	
	/**
	 * 
	 * @Title: parsePointArray 
	 * @Description: 分解点字符串，例如：103.98703;30.632902,103.98706;30.63293,103.9871;30.632946,103.9871;30.632946,103.9871;30.632946
	 * @param s
	 * @param seprator1  经纬度之间的分隔符
	 * @param seprator2 两点间的分隔符
	 * @return
	 * @return: GeoPointTools[]
	 * @throws Exception 
	 */
	public static List<Point> parsePointArray(String s, String seprator1, String seprator2) throws Exception {
		String[] pointsArray = s.split(seprator2);
		Vector<Point> ret = new Vector<>();
		for(int i=0;i<pointsArray.length;++i) {
			String[] ll = pointsArray[i].split(seprator1);
			if(ll.length < 2)
				throw new Exception("错误的格式"+s);
			ret.add(new Point(Double.parseDouble(ll[0]), Double.parseDouble(ll[1])));
		}
		return ret;
	}

	public static Double getMecartoDistance(Collection<Point> pts) {
		Point pt1 = null;
		Double sum = .0;
		for(Point pt:pts) {
			if(pt1 != null) {
				sum += getMecartoDistance(pt1, pt);
			}
			pt1 = pt;
		}
		return sum;
	}

	/**
	 * 
	 * @Title: getNearestPoint 
	 * @Description: 获得最近的点的下标
	 * @param pt
	 * @param dirPoints
	 * @param minDistance 最小距离，为空表示不需要
	 * @return  -1表示没找到
	 * @return: int
	 */
	public static int getNearestPoint(Point ptIn, Collection<Point> points, Long minDistance) {
		
		Double minDist = Double.MAX_VALUE;
		int ret = -1;
		Iterator<Point> iter = points.iterator();
		for(int i=0;iter.hasNext();++i) {
			Point pt = iter.next();
			Double distance = getMecartoDistance(ptIn, pt);
			if(minDistance != null && distance > minDistance)
				continue;
			if(minDist > distance) {
				minDist = distance;
				ret = i;
			}
		}
		return ret;
	}

	public static double scorePoints(Collection<Point> points, double threshold) {
		if(threshold<=0)
			threshold = 100;
		
		if(points.isEmpty())
			return .0;
		
		Iterator<Point> iter = points.iterator();
		Point lastPt = null;
		double sum = .0;
		while(iter.hasNext()) {
			Point pt = iter.next();
			if(lastPt != null) {
				Double d = getMecartoDistance(pt, lastPt);
				double s = (d<threshold || d<=0) ? 0 : (d-threshold)/d;
//				System.out.println(String.format("{} {}", d, s));
				sum += s;
			}			
			lastPt = pt;
		}
		return sum/points.size();
	}
	
	public static Point meanPoint(Point ptIn, Collection<Point> points, double minMeanDistance) {
		double meanX = .0;
		double meanY = .0;
		int meanNum = 0;
		for(Point pt:points) {
			if(ptIn.equals(pt))
				continue;
			double d = getMecartoDistance(ptIn, pt);
			if(d < minMeanDistance) {
				meanX += pt.getX();
				meanY += pt.getY();
				++meanNum;
			}
		}
		
		if(meanNum < 1)
			return ptIn;
		
		return new Point(meanX/meanNum, meanY/meanNum);
	}
	
	public static Collection<Point> smooth(Collection<Point> points, double minMeanDistance) {
		
		Vector<Point> ret = new Vector<>();
		
		for(Point pt:points) {
			ret.add(meanPoint(pt, points, minMeanDistance));
		}
		
		return ret;
		
	}


}
