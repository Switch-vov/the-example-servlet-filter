package com.pc.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.pc.user.domain.User;
import com.pc.user.utils.C3P0Utils;

/**
 * UserDao
 * 
 * @author Switch
 * @data 2016年10月24日
 * @version V1.0
 */
public class UserDao {
	/**
	 * 查找用户
	 * 
	 * @param user
	 *            须查找User信息
	 * @return 数据库中User
	 * @throws SQLException
	 */
	public User findUser(User user) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from user where username = ? and password = ?";
		Object[] params = { user.getUsername(), user.getPassword() };
		return queryRunner.query(sql, new BeanHandler<>(User.class), params);
	}

}
