package com.beiyelin.app.api;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beiyelin.service.footstone.dao.UserMapper;
import com.beiyelin.service.footstone.model.User;

@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserMapper userMapper;
	
	@RequestMapping(value="/{userID}",method=RequestMethod.GET)
	public ResponseEntity<User> getUserbyID(@PathVariable("userID") String userID){
		User user=userMapper.selectByPrimaryKey(userID);
		if(user == null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
				
	}
}
