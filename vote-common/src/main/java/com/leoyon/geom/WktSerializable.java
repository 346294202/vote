/**   
 * Copyright © 2017 dibu. All rights reserved.
 * 
 * @Title: WktSerializable.java 
 * @Prject: dibu-db
 * @Package: com.daibugroup.db.po.geom 
 * @Description: TODO
 * @author:wangjiang
 * @date: 2017年7月13日 下午4:32:29 
 */
package com.leoyon.geom;

/** 
 * @ClassName: WktSerializable 
 * @Description: WKT格式
 * @author:wangjiang
 * @date: 2017年7月13日 下午4:32:29  
 */
public interface WktSerializable {

	String asText();
	void fromText(String wkt) throws WktException;
}
