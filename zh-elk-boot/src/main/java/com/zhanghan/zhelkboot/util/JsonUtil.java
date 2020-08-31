/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：JsonUtil.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhelkboot.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import io.micrometer.core.instrument.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {
	private final static Logger log = LoggerFactory.getLogger(JsonUtils.class);

	private JsonUtil() {

	}

	/**
	 * Object对象转换为JSON格式
	 * 
	 * 例如List对象、JavaBean对象、JavaBean对象数组、Map对象、List Map对象
	 * 
	 * @param object
	 *            传入的Object对象
	 * @return object的JSON格式字符串
	 */
	public static String objtoJson(Object object) {
		String json = "";
		ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
		try {
			objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false); // 配置不写MAP中value为null的key
			json = objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			log.error("objtoJson parse error", e);
		}
		return json;
	}

	/**
	 * Object对象转换为JSON格式(变量为下划线格式) 如： {"user_name":"bflee","id_number":"123456"}
	 * 例如List对象、JavaBean对象、JavaBean对象数组、Map对象、List Map对象
	 * 
	 * @param object
	 *            传入的Object对象
	 * @return object的JSON格式字符串
	 */
	public static String object2UnderLineJson(Object object) {
		String json = "";
		ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
		try {
			objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false); // 配置不写MAP中value为null的key
			objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

			json = objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			log.error("objtoJson parse error", e);
		}
		return json;
	}

	/**
	 * JSON转换为Object对象
	 * 
	 * @param jsonString
	 *            JSON字符串
	 * @param c
	 *            要转换成的类的类型
	 * @return Object对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object jsonToObject(String jsonString, Class c) {

		if (jsonString == null || "".equals(jsonString)) {
			return null;

		} else {

			// Jackson方式将Json转换为对象
			MappingJsonFactory f = new MappingJsonFactory();
			try {
				JsonParser parser = f.createParser(jsonString);
				return parser.readValueAs(c);

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return null;
			}
		}
	}

	/**
	 * 数组格式JSON串转换为ObjectList对象
	 * 
	 * @param
	 * @param jsonString
	 *            JSON字符串
	 * @param tr
	 *            TypeReference,例如: new TypeReference< List<Album> >(){}
	 * @return ObjectList对象
	 */

	public static <E> Object jsonToGenericObject(String jsonString, TypeReference<List<E>> tr) {

		if (jsonString == null || "".equals(jsonString)) {
			return null;

		} else {

			// Jackson方式将Json转换为对象
			MappingJsonFactory f = new MappingJsonFactory();
			try {
				JsonParser parser = f.createParser(jsonString);
				return parser.readValueAs(tr);

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return null;
			}
		}
	}

	/**
	 * JSON转换为传入映射对象
	 * 
	 * @param jsonString
	 *            JSON字符串
	 * @param t
	 *            要转换成的类的类型
	 * @return Class<T> 对象
	 */
	public static <T> T jsonToBean(String jsonString, Class<T> t) throws Exception {

		if (jsonString == null || "".equals(jsonString)) {
			return null;

		} else {
			// Jackson方式将Json转换为对象
			MappingJsonFactory f = new MappingJsonFactory();
			JsonParser parser = f.createParser(jsonString);
			return parser.readValueAs(t);
		}
	}

	/**
	 * 数组格式JSON串转换为ObjectList对象
	 * 
	 * @param
	 * @param jsonString
	 *            JSON字符串
	 * @param tr
	 *            TypeReference,例如: new TypeReference< List<Album> >(){}
	 * @return ObjectList对象
	 */
	public static <E> List<E> jsonToGenericBean(String jsonString, TypeReference<List<E>> tr) {

		if (jsonString == null || "".equals(jsonString)) {
			return null;

		} else {

			// Jackson方式将Json转换为对象
			MappingJsonFactory f = new MappingJsonFactory();
			try {
				JsonParser parser = f.createParser(jsonString);
				return parser.readValueAs(tr);

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return null;
			}
		}
	}

	/**
	 * 数组格式JSON串转换为ObjectMap对象
	 * 
	 * @param
	 * @param jsonString
	 *            JSON字符串
	 * @param tr
	 *            TypeReference,例如: new TypeReference< Map<K, V> >(){}
	 * @return ObjectMap对象
	 */
	public static <K, V> Map<K, V> jsonToGenericMap(String jsonString, TypeReference<Map<K, V>> tr) {

		if (jsonString == null || "".equals(jsonString)) {
			return null;

		} else {

			// Jackson方式将Json转换为对象
			MappingJsonFactory f = new MappingJsonFactory();
			try {
				JsonParser parser = f.createParser(jsonString);
				return parser.readValueAs(tr);

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return null;
			}
		}
	}

	/**
	 * 下划线变量JSON转换为传入映射对象
	 * 
	 * @param jsonString
	 *            JSON字符串
	 * @param t
	 *            要转换成的类的类型
	 * @return Class<T> 对象
	 */
	public static <T> T underLineJsonToBean(String jsonString, Class<T> t) {

		if (jsonString == null || "".equals(jsonString)) {
			return null;
		} else {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
			T obj = null;
			try {
				obj = (T) mapper.readValue(jsonString, t);
			} catch (JsonParseException e) {
				log.error("Json转换异常", e);
			} catch (JsonMappingException e) {
				log.error("JsonMappingException", e);
			} catch (IOException e) {
				log.error("Json转换异常IOException", e);
			}
			return obj;
		}
	}

	/**
	 * 下划线变量JSON转换为传入映射对象，数组格式JSON串转换为ObjectList对象
	 * 
	 * @param
	 * @param jsonString
	 *            JSON字符串
	 * @param tr
	 *            TypeReference,例如: new TypeReference< List<Album> >(){}
	 * @return ObjectList对象
	 */
	public static <E> List<E> underLineJsonToGenericBean(String jsonString, TypeReference<List<E>> tr) {
		if (jsonString == null || "".equals(jsonString)) {
			return null;
		} else {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
			try {
				return mapper.readValue(jsonString, tr);
			} catch (JsonParseException e) {
				log.error("Json转换异常", e);
			} catch (JsonMappingException e) {
				log.error("Json转换异常", e);
			} catch (IOException e) {
				log.error("Json转换异常", e);
			}
			return null;
		}
	}
}