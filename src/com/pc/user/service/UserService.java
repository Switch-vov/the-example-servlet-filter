package com.pc.user.service;

import java.sql.SQLException;

import com.pc.user.dao.UserDao;
import com.pc.user.domain.User;

/**
 * 用户服务类
 * 
 * @author Switch
 * @data 2016年10月24日
 * @version V1.0
 */
public class UserService {
	/**
	 * 查找用户
	 * 
	 * @param user
	 *            须查找User信息
	 * @return 数据库中User
	 * @throws SQLException 
	 */
	public User findUser(User user) throws SQLException {
		UserDao userDao = new UserDao();
		return userDao.findUser(user);
	}

}
