package com.beiyelin.service.footstone.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import com.beiyelin.test.BaseTest;
import com.beiyelin.test.TestConstant;
import com.beiyelin.service.footstone.model.Role;
import com.beiyelin.service.footstone.model.RoleUser;

public class RoleUserMapperTest extends BaseTest {
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleUserMapper roleUserRecordMapper;
	
	
	@Test
	@Transactional
	public void addRoleUser() {
		RoleUser roleUserRecord;
		
		List<Role> rList = roleMapper.selectRolesByUserPrimaryKey(TestConstant.testUserID);
		if(rList.isEmpty()){
			roleUserRecord = new RoleUser();
			roleUserRecord.setId(UUID.randomUUID().toString());
			roleUserRecord.setRoleid(TestConstant.testRoleID);
			roleUserRecord.setUserid(TestConstant.testUserID);
			
			assertEquals(1, roleUserRecordMapper.insert(roleUserRecord));
		}
	}

}
