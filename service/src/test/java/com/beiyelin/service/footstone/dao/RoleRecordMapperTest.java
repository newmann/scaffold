package com.beiyelin.service.footstone.dao;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.beiyelin.service.footstone.constant.AccountStatusCST;
import com.beiyelin.service.footstone.model.Role;
import com.beiyelin.test.BaseTest;
import com.beiyelin.test.TestConstant;

public class RoleRecordMapperTest extends BaseTest {
	@Resource
	private RoleMapper roleRecordMapper;
	
	@Test
	@Transactional
	public void AddRole(){
		Role roleRecord;
		String roleID;
		//c
		//创建缺省的管理员权限
		roleRecord = roleRecordMapper.selectByPrimaryKey(TestConstant.testRoleID);
		
		if(roleRecord == null){
			roleID = TestConstant.testRoleID;
			roleRecord = new Role();
			roleRecord.setId(roleID);
			roleRecord.setRolecode("admin");
			roleRecord.setRolename("管理员组");
		
		}else {
			roleID = UUID.randomUUID().toString();
			roleRecord = new Role();
			roleRecord.setId(roleID);
			roleRecord.setRolecode("test01");
			roleRecord.setRolename("测试组01");
			
		}
		roleRecord.setCreator(TestConstant.testUserID);
		roleRecord.setCreatetime(new Date());
		roleRecord.setSpaceid(TestConstant.testDefaultOrgID);
		roleRecord.setUpdater(TestConstant.testUserID);
		roleRecord.setUpdatetime(new Date());
		roleRecord.setStatus(AccountStatusCST.Normal);
		roleRecordMapper.insert(roleRecord);
	}

}
