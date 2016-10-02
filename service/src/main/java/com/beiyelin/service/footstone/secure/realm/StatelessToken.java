package com.beiyelin.service.footstone.secure.realm;

import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.shiro.authc.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 先用最简单的方式实现一个。然后在用JWT来完成
 *
 */

public class StatelessToken implements AuthenticationToken{

    private static Logger logger = LoggerFactory.getLogger(StatelessToken.class);

	public final static String STATELESS_TOKEN_SECRET="asdfqnaosdhfnowqen";

    //采用JWT的格式
	private String userID;//登录用户id
	private String issuer;//token发出方
	private String subject;//token的内容
	private long ttlMills;//token的有效期

    //加密算法
	public final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

	private String username;
	private Map<String,?> params;
	private String clientDigest;

	public StatelessToken(String username,  Map<String, ?> params, String clientDigest) {
		this.username = username;
		this.params = params;
		this.clientDigest = clientDigest;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Map<String, ?> getParams() {
		return params;
	}

	public void setParams(Map<String, ?> params) {
		this.params = params;
	}

	public String getClientDigest() {
		return clientDigest;
	}

	public void setClientDigest(String clientDigest) {
		this.clientDigest = clientDigest;
	}

	//	public StatelessToken(String userID, String issuer, String subject, long ttlMills) {
//		super();
//		this.userID = userID;
//		this.issuer = issuer;
//		this.subject = subject;
//		this.ttlMills = ttlMills;
//	}

	// 返回用户验证信息
	@Override
	public Object getCredentials() {
        logger.debug("获取用户凭证："+clientDigest);
		// TODO Auto-generated method stub
		return clientDigest;
	}

	public Object getPrincipal() {
		// TODO Auto-generated method stub
        logger.debug("获取用户名：" + username);
		return username;
	}
	
}
