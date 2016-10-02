package com.beiyelin.service.footstone.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.beiyelin.service.footstone.model.Permission;
import com.beiyelin.service.footstone.model.RolePermission;
import com.beiyelin.test.BaseTest;
import com.beiyelin.test.TestConstant;

public class RolePermissionRecordMapperTest extends BaseTest {
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	@Test
	@Transactional
	public void addRolePermission() {
		RolePermission record;
		List<Permission> pList;
		
		pList = permissionMapper.selectPermissionsByRolePrimaryKey(TestConstant.testRoleID);
		
		if (!pList.isEmpty()){
			assertEquals(3, rolePermissionMapper.deleteRolePermissionByRolePrimaryKey(TestConstant.testRoleID));
		}
		record = new RolePermission();
		
		record.setId(UUID.randomUUID().toString());
		record.setRoleid(TestConstant.testRoleID);
		record.setPermissionid(TestConstant.testPermissionUserModuleID);
		
		assertEquals(1, rolePermissionMapper.insert(record));

		record = new RolePermission();
		record.setId(UUID.randomUUID().toString());
		record.setRoleid(TestConstant.testRoleID);
		record.setPermissionid(TestConstant.testPermissionUserCreateID);
		assertEquals(1, rolePermissionMapper.insert(record));
		
		record = new RolePermission();
		record.setId(UUID.randomUUID().toString());
		record.setRoleid(TestConstant.testRoleID);
		record.setPermissionid(TestConstant.testPermissionUserDeleteID);
		assertEquals(1, rolePermissionMapper.insert(record));
		
		
	}

}
