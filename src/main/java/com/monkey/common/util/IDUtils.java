package com.monkey.common.util;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class IDUtils {

	public static String getUUID(){
		String id = UUID.randomUUID().toString();
		return id.replaceAll("-", "");
	}
	
	public static String getRandomId(int x){
		return RandomStringUtils.randomAlphanumeric(x);
	}
	
}
