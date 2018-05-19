package com.study.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.content.Context;

public class PP {
//	public static List<Properties> plist=new ArrayList<Properties>();
//	Context context;
//	String path;
//	Properties properties = null;
//
//	public PP(Context context,String path){
//		this.context=context;
//		this.path=path;
//		load();
//	}
//
//	public Properties getProperties() {
//		return properties;
//	}
//
//	private PP load(){
////		InputStream is = context.getAssets().open("test.properties");
////		pro.load(is);
////		InputStream is = context.getResources().openRawResource(R.raw.test);
//
//		properties = new Properties();
//		InputStream is=null;
//		try {
//			is=context.getAssets().open(path);
//			properties.load(is);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
////			if(is!=null)
////				try {
////					is.close();
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
//		}
//		return this;
//	}
//
//	public PP save() {
//		FileOutputStream s = null;
//		try {
//			s = new FileOutputStream(path, false);
//			properties.store(s, "");
//		} catch (Exception e){
//			e.printStackTrace();
//		}finally{
//			if(s!=null)
//				try {
//					s.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//		}
//		return this;
//	}
//
//	public String get(String key){
//		return properties.getProperty(key);
//	}
//
//	public Integer getInteger(String key){
//		return Integer.parseInt(properties.getProperty(key));
//	}
//
//	public Boolean getBoolean(String key){
//		return Boolean.parseBoolean(properties.getProperty(key));
//	}
//
//	public Double getDouble(String key){
//		return Double.parseDouble(properties.getProperty(key));
//	}
//
//	public PP put(String key,Object value){
//		properties.put(key, value);
//		return this;
//	}
//
//	////////////////////////////////////////////////////
//	public static PP load(Context context,String path){
//		PP pp=new PP(context, path);
//		if(pp!=null)
//			plist.add(pp.properties);
//		return pp;
//	}
	final Properties p;
	static PP instance;
	private PP(Properties p){
		this.p = p;
	}

	public static PP load(Properties p){
		if(p == null)return  null;
		instance = new PP(p);
		return instance;
	}

	public static PP load(InputStream in){
		if(in == null)return  null;
		instance = new PP(getConfigProperties(in));
		return instance;
	}

	public static PP load(Context context,String path){
		if(context == null || path == null)return  null;
		try {
			instance = new PP(getConfigProperties(context.getAssets().open(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instance;
	}

	/**
	 * 得到netconfig.properties配置文件中的所有配置属性
	 *
	 * @return Properties对象
	 */
	public static Properties getConfigProperties(String filepath) {
		try {
			Properties props = new Properties();
			InputStream in = new FileInputStream(filepath);
			return getConfigProperties(in);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public static Properties getConfigProperties( InputStream in) {
		Properties props = new Properties();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(in));
			props.load(bufferedReader);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(bufferedReader != null ){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return props;
	}

	/**
	 * 保存
	 * @param properties
	 * @param targetPath
	 */
	public static void save(Properties properties,String targetPath){
		OutputStream fos;
		try {
			fos = new FileOutputStream(targetPath);
			properties.store(fos, null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 支持标签的解析
	 * @param key
	 * @return
	 */
	public static List<String> getValues(String key,String split){
		String value = instance.p.getProperty(key);
		List<String> list = new ArrayList<>();
		if(value != null && value.trim().length() > 0){
			for (String str: value.split(split)) {
				String v = getValue(str);
				if(v != null && v.trim().length() > 0){
					list.add(v);
				}else {
					list.add(str);
				}
			}
		}
		return list;
	}

	/**
	 * 支持标签的解析
	 * @param key
	 * @return
	 */
	public static String[] getValueArray(String key,String split){
		List<String> list = getValues(key,split);
		String[] arr = new String[list.size()];
		int i=0;
		for (String str:list ) {
			arr[i++] = str;
		}
		return arr;
	}

	/**
	 * 支持标签的解析
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		String value = instance.p.getProperty(key);
		if(value != null && value.trim().length() > 0 && instance.p.containsKey(value))return getValue(value);
		return value;
	}
}
