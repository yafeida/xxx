package com.monkey.common.util;

import java.util.UUID;

public class IDUtils {

	public static String getUUID(){
		String id = UUID.randomUUID().toString();
		return id.replaceAll("-", "");
	} 
	
}
