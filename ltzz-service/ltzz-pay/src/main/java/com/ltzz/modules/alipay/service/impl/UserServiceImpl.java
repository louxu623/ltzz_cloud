package com.ltzz.modules.alipay.service.impl;

import com.ltzz.modules.alipay.dao.UserDao;
import com.ltzz.modules.alipay.entity.User;
import com.ltzz.modules.alipay.entity.UserExample;
import com.ltzz.modules.alipay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public void saveUser(User user) {
		userDao.insert(user);
	}

	@Override
	public void updateUserById(User user) {
		userDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public void deleteUserById(String userId) {
		userDao.deleteByPrimaryKey(userId);
	}

	@Override
	public User getUserById(String userId) {
		
		return userDao.selectByPrimaryKey(userId);
	}
	
	@Override
	public List<User> getUserList() {
		
		UserExample ue = new UserExample();
		List<User> userList = userDao.selectByExample(ue);
		
		return userList;
	}

}
