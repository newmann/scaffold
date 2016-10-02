package com.beiyelin.service.footstone.service;

import java.util.HashMap;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

/**
 * 用户的密码加密
 * @author xinsheng.hu
 *
 */
@Service
public class PasswordHelper {
	public final static String RESULTMAP_SALT_TAG = "s";
	public final static String RESULTMAP_PASSWORD_TAG = "p";
	
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private String algorithmName = "md5";
	private final int hashIterations = 2;
	
	/**
	 * 对字符串进行md5加密，并将加密后的字符串和salt通过map返回。返回格式：
	 * {“salt”:XXXX;"password":XXXX}
	 * @param password 待加密的字符串
	 * @param salts salt，如果没有传入，则自动生成，并返回。
	 * @return
	 */
	public HashMap<String , String > encryptPassword(String password,String...salts){
		String salt;
		HashMap<String , String> rb = new HashMap<String, String>();
		
		if(salts.length == 0){
			salt = salts[0];//只取第一个值
		}else {
			salt = randomNumberGenerator.nextBytes().toHex();
		}
		rb.put(RESULTMAP_SALT_TAG, salt);
		String newPassword = new SimpleHash(algorithmName,
				password,
				ByteSource.Util.bytes(salt),
				hashIterations).toHex();
		rb.put(RESULTMAP_PASSWORD_TAG, newPassword);
		return rb;

	}
	/**
	 * 判断输入的密码是否与加密后的密码相同
	 * @param targetPassword 待判断的密码，明文
	 * @param assertPassword 原密码，加密字符串
	 * @param assertSalt 原密码加密用的salt
	 * @return
	 */
	public Boolean assertPasswordEqual(String targetPassword, String assertPassword, String assertSalt) {
		HashMap<String,	String> rb;
		rb = encryptPassword(targetPassword, assertSalt);
		return assertPassword.equals(rb.get(RESULTMAP_PASSWORD_TAG));

	}
}
