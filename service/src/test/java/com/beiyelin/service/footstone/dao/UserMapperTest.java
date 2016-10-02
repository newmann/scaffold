package com.beiyelin.service.footstone.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.beiyelin.service.footstone.model.User;
import com.beiyelin.service.footstone.constant.GenderCST;
import com.beiyelin.service.footstone.constant.UserCertificateTypeCST;
import com.beiyelin.test.BaseTest;
import com.beiyelin.test.TestConstant;



public class UserMapperTest extends BaseTest {
	@Resource
	private UserMapper userMapper;
	
	private static Logger logger = LoggerFactory.getLogger(UserMapperTest.class);
	
	@Test
	@Transactional
	@Rollback(true)
	public void AddUser(){
		logger.info("开始添加User"); 
		
		User user;
		
		String userID;
		int i;
		user = userMapper.selectByPrimaryKey(TestConstant.testUserID);
		if(user ==null){
			logger.info("开始添加缺省用户kidson");
			user = new User();
			user.setId(TestConstant.testUserID);
			user.setNickname("kidson");
			user.setUsertype(UserCertificateTypeCST.IDCard);
			user.setUsername("kidson");
			user.setTruename("胡新生");
			user.setSex(GenderCST.Male);
			user.setCreatetime(new Date());
			user.setPassword("");
			i = userMapper.insert(user);
			assert(i == 1);
		}else{
			
			userID = UUID.randomUUID().toString();
			user = new User();
			user.setId(userID);
			user.setNickname("TestMan01");
			user.setUsertype(UserCertificateTypeCST.IDCard);
			user.setUsername("kidson");
			user.setTruename("huxinsheng");
			user.setSex(GenderCST.Female);
			user.setCreatetime(new Date());
			user.setPassword("");
			logger.info("开始添加测试用户"+user.getId());
			userMapper.insert(user);
			
			User user2;
			user2 = userMapper.selectByPrimaryKey(userID);
			assertEquals(user2.getId(), user.getId());
			userMapper.deleteByPrimaryKey(userID);

		}
				
	}

}
