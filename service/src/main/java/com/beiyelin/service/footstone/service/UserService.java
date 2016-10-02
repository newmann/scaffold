package com.beiyelin.service.footstone.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beiyelin.service.footstone.dao.PermissionMapper;
import com.beiyelin.service.footstone.dao.RoleMapper;
import com.beiyelin.service.footstone.dao.RoleUserMapper;
import com.beiyelin.service.footstone.dao.UserMapper;
import com.beiyelin.service.footstone.model.RoleUser;
import com.beiyelin.service.footstone.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RoleUserMapper roleUserRecordMapper;
	
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private PasswordHelper passwordHelper;
	
	
	/**
	 * 对用户的密码进行加密
	 * @param user
	 */
	public void encryptUserPassword(User userRecord){
		userRecord.setPassword(passwordHelper.encryptPassword(userRecord.getPassword())
				.get(passwordHelper.RESULTMAP_PASSWORD_TAG));
	}
	
	/**
     * Query the roles of a user by user's Primary Key
     * 只提取用户的基本信息，不含角色、权限等
     * @param id ,user primary key
     * @return set of role
     */
    public User selectSimpleUserByPrimaryKey(String id){
    	
    	return userMapper.selectByPrimaryKey(id);
    	
    };
    /**
     * 将用户的所有信息提取出来
     * @param id
     * @return
     */
    public User selectUserByPrimaryKey(String id){
    	User user = new User();
    	user= userMapper.selectByPrimaryKey(id);
    	
    	user.setRoleList(roleMapper.selectRolesByUserPrimaryKey(id));

    	user.setPermissionList(permissionMapper.selectPermissionsByUserPrimaryKey(id));
    	return user;
    };

    @Transactional
    public int insertSimpleUser(User user){
    	if (user.getSalt().length() == 0) {
			encryptUserPassword(user);
		}
    	return userMapper.insert(user);
    }
    
    @Transactional
    public int insertUserRole(String userID,String roleID){
    	RoleUser record;
    	record = new RoleUser();
    	record.setId(UUID.randomUUID().toString());
    	record.setUserid(userID);
    	record.setRoleid(roleID);
    	return roleUserRecordMapper.insert(record);
    }
}
