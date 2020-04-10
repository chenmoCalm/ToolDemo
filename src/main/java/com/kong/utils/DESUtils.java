package com.kong.utils;



import com.alibaba.fastjson.JSONObject;


import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * DES 加密工具类
 *
 */
public class DESUtils {

	private String desKey;
	// 指定DES加密解密所用的密钥
	private static Key key;

	/**
	 * 加密key为空
	 */
	public DESUtils() {
		setkey(desKey);
	}

	/**
	 * 设置加密key
	 *
	 * @param keyStr
	 * 加密key值
	 */
	public DESUtils(String keyStr) {
		setkey(keyStr);
	}

	/**
	 * 设置加密的校验码
	 */
	private void setkey(String keyStr) {
		try {
			DESKeySpec objDesKeySpec = new DESKeySpec(keyStr.getBytes("UTF-8"));
			SecretKeyFactory objKeyFactory = SecretKeyFactory.getInstance("DES");
			key = objKeyFactory.generateSecret(objDesKeySpec);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 对字符串进行DES加密，返回BASE64编码的加密字符串
	public final String encryptString(String str) {

		byte[] bytes = new byte[0];
		try {
			bytes = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(bytes);
			return Base64.encodeBase64String(encryptStrBytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 对BASE64编码的加密字符串进行解密，返回解密后的字符串
	public final String decryptString(String str) {
		try {
			byte[] strBytes = str.getBytes("UTF-8");
			byte[] bytes = Base64.decodeBase64(strBytes);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			bytes = cipher.doFinal(bytes);
			return new String(bytes);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	// 提供main函数，方便转换加密字符
	public static void main(String[] args) {
		DESUtils utils = new DESUtils("bestvike");//("B523082D");
//		System.out.println(utils.encryptString(""));
		Map map = new HashMap<>();
//		map.put("loginType","1");
//		map.put("userName","15275119929");

		String json = JSONObject.toJSONString(map);
		System.out.println(utils.encryptString(json));

		try {
//			boolean result = new BCryptPasswordEncoder().matches(EncryptUtils.base64Decode("666666"),"$2a$10$0Y65UaA173ewgIOXlo9KJeOhvTfRsHPLdybF2TskqCtwrpZemWZkO");
//			System.out.println("result = "+result);
		} catch (Exception e) {

		}

	}

}
