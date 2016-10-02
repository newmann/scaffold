package com.beiyelin.service.footstone.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.beiyelin.service.footstone.model.Permission;
import com.beiyelin.test.BaseTest;
import com.beiyelin.test.TestConstant;

public class PermissionRecordMapperTest extends BaseTest {

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Test
	@Transactional
	@Rollback(false)
	public void AddPermission(){
		Permission permissionRecord;
		int b;
		permissionRecord = permissionMapper.selectByPrimaryKey(TestConstant.testPermissionUserModuleID);
		if (permissionRecord != null){
			permissionMapper.deleteByPrimaryKey(TestConstant.testPermissionUserModuleID);
		}
		permissionRecord = new Permission();
		permissionRecord.setId(TestConstant.testPermissionUserModuleID);
		permissionRecord.setName("用户");
		permissionRecord.setModuleid(TestConstant.testPermissionUserModuleID);
		permissionRecord.setIsmodule(true);
		permissionRecord.setIsfunction(false);
		permissionRecord.setIsfield(false);
		permissionRecord.setDescription("用户管理模块，维护用户账户、验证审核等");
		b = permissionMapper.insert(permissionRecord);
		assert( b == 1);

		permissionRecord = permissionMapper.selectByPrimaryKey(TestConstant.testPermissionUserCreateID);
		if (permissionRecord != null){
			permissionRecord = new Permission();
			permissionRecord.setId(TestConstant.testPermissionUserCreateID);
			permissionRecord.setName("创建用户");
			permissionRecord.setModuleid(TestConstant.testPermissionUserModuleID);
			permissionRecord.setIsmodule(false);
			permissionRecord.setIsfunction(true);
			permissionRecord.setIsfield(false);
			permissionRecord.setDescription("创建用户账户");
			b = permissionMapper.updateByPrimaryKey(permissionRecord);
			assert( b == 1);
		}else {
			permissionRecord = new Permission();
			permissionRecord.setId(TestConstant.testPermissionUserCreateID);
			permissionRecord.setName("创建用户");
			permissionRecord.setModuleid(TestConstant.testPermissionUserModuleID);
			permissionRecord.setIsmodule(true);
			permissionRecord.setIsfunction(false);
			permissionRecord.setIsfield(false);
			permissionRecord.setDescription("创建用户账户");
			b = permissionMapper.insert(permissionRecord);
			assert( b == 1);
		}

		
		permissionRecord = permissionMapper.selectByPrimaryKey(TestConstant.testPermissionUserDeleteID);
		if (permissionRecord != null){
			permissionRecord = new Permission();
			permissionRecord.setId(TestConstant.testPermissionUserDeleteID);
			permissionRecord.setName("删除用户");
			permissionRecord.setModuleid(TestConstant.testPermissionUserModuleID);
			permissionRecord.setIsmodule(true);
			permissionRecord.setIsfunction(false);
			permissionRecord.setIsfield(false);
			permissionRecord.setDescription("删除用户账户,第二次提交，但不被修改。");
			b = permissionMapper.updateByPrimaryKeySelective(permissionRecord);
			assert( b == 1);
			permissionRecord = permissionMapper.selectByPrimaryKey(TestConstant.testPermissionUserDeleteID);
			
			assertTrue(permissionRecord.getDescription().equals("删除用户账户"));
			
		} else{
			permissionRecord = new Permission();
			permissionRecord.setId(TestConstant.testPermissionUserDeleteID);
			permissionRecord.setName("删除用户");
			permissionRecord.setModuleid(TestConstant.testPermissionUserModuleID);
			permissionRecord.setIsmodule(false);
			permissionRecord.setIsfunction(true);
			permissionRecord.setIsfield(false);
			permissionRecord.setDescription("删除用户账户");
			b = permissionMapper.insert(permissionRecord);
			assert( b == 1);
		}
		
	}

}
