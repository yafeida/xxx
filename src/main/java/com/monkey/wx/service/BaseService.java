package com.monkey.wx.service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.monkey.common.util.MD5Utils;
import com.monkey.common.util.RSAEncrypt;

@Service
public class BaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 获取请求参数
	 * @param r
	 * @return
	 */
	public Map<String,Object> getMaps(HttpServletRequest req){
		Map<String,Object> m=new HashMap<String, Object>();
		Enumeration<String> e=req.getParameterNames();
		while(e.hasMoreElements()){
			String s=(String) e.nextElement();
			m.put(s, req.getParameter(s).trim());
		}
		return m;
	}
	
	
	/**
	 * 获取4位序列码
	 * 
	 * @return
	 */
	public static int getFourSquece() {
		return (int) ((Math.random() * 9 + 1) * 1000);
	}
	
	/**
	 * 获取签名,采用私钥加密转16进制
	 * @param svcCont	加密字符串
	 * @param privateKey	私钥
	 * @return	
	 */
	public  String generateSignature(String svcCont,String privateKey) {
		 String signature = "";
		 System.out.println("svcCont:  "+svcCont);
		 try {
				RSAEncrypt rsaPriEncrypt = new RSAEncrypt();//私钥
				rsaPriEncrypt.loadPrivateKey(privateKey);
				//对报文摘要用MD5进行加密
				String paramMD5 = MD5Utils.md5(svcCont);
				System.out.println("paramMD5:  "+paramMD5);
				//对MD5加密后的摘要用 密钥 进行加密得到数据签名
				byte[] signatureBytes = rsaPriEncrypt.encodeBytePrivate(paramMD5.getBytes(),rsaPriEncrypt.getPrivateKey());
				signature = Hex.encodeHexString(signatureBytes);
				System.out.println("signature:  "+signature);
				return signature;
			} catch (Exception e) {
		    	logger.error("私钥加密错误", e);
			}
		 return signature;
	}
}
