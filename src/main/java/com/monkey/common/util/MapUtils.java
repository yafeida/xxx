package com.monkey.common.util;

import java.util.Map;

public class MapUtils {

	public static String toString(Map<String,Object> map){
		String str = "";
		for(String key : map.keySet()){
			str += key+":"+map.get(key)+"|";
		}
		return str;
	}
	
}
