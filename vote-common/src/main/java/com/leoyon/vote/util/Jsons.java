package com.leoyon.vote.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.SimpleType;

public class Jsons {
	
	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * bean to json string conver
	 * @param obj
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String beanToJson(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		return mapper.writeValueAsString(obj);
	}
	
	
	/**
	 * bean 转 bytes
	 * @param obj
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static byte[] beanToBytes(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		return mapper.writeValueAsBytes(obj);
	}
	
	/**
	 * 将map 转为对应的实体bean
	 * @param map
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T mapToBean(Map map,Class<T> clazz) {
		return mapper.convertValue(map, clazz);
	}
	
	/**
	 * bean 转换map
	 * @param obj
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> beanToMap(Object obj) throws JsonParseException, JsonMappingException, JsonGenerationException, IOException {
		return jsonToBean(beanToJson(obj), Map.class);
	}
	
	
	/**
	 * json string to bean conver
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T jsonToBean(String jsonStr,Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(jsonStr, clazz);
	}
	
	/**
	 * 将字节数组转为对应的实体
	 * @param bytes
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T bytesToBean(byte[] bytes,Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(bytes, clazz);
	}

	/**
	 * json to map
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> jsonToMap(String jsonStr) throws IOException {
		return mapper.readValue(jsonStr, Map.class);
	}
	
	/**
	 * json to list
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	public static List<Object> jsonToList(String jsonStr) throws IOException {
		return mapper.readValue(jsonStr, List.class);
	}
	
	/**
	 * json to list
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> jsonToList(String jsonStr, Class<T> clazz) throws IOException {
		return mapper.readValue(jsonStr, CollectionType.construct(List.class, SimpleType.construct(clazz)));
	}

	/**
	 * 获取Mapper
	 * @return
	 */
	public static  ObjectMapper getMapper(){
		return  mapper;
	}
	

}
