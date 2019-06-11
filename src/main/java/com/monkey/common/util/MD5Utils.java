package com.monkey.common.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.commons.codec.binary.Hex;

public class MD5Utils {

	protected MD5Utils(){
		
	}
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	public static String genSalt(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String encrypt(String username, String pswd ,String salt) {
		return new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(salt),HASH_ITERATIONS).toHex();
	}
	
	/**
	 * MD5摘要
	 * 
	 * @param s
	 * @return
	 */
	public final static String md5(String s) {
		try {
			byte[] btInput = s.getBytes("UTF-8");
			// 获得MD5摘要算法 生成MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			return Hex.encodeHexString(md);
		} catch (Exception e) {
			return null;
		}
	}

	
	/**
	 * MD5加密
	 * @param origin
	 * @return
	 */
	public static String ToMD5(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToString(md.digest(resultString.getBytes()));
		} catch (Exception exception) {
		}
		return resultString;
	}
	
	/**
	 *  字节数据转十六进制字符串
	 * @param b
	 * @return
	 */
	public static String byteArrayToString(byte b[]) {
		StringBuffer bths = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			bths.append(byteToHexString(b[i]));

		return bths.toString();
	}
	
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return (new StringBuilder(String.valueOf(hexDigits[d1]))).append(
				hexDigits[d2]).toString();
	}



}
