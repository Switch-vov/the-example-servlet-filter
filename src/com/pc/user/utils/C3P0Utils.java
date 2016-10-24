package com.pc.user.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0的JDBC工具类，必须提供c3p0-copnfig.xml文件
 * 
 * @author Switch
 * @data 2016年10月17日
 * @version V1.0
 */
public class C3P0Utils {
	// 加载连接配置获取数据源
	// 带名称的配置
	// private DataSource dataSource = new ComboPooledDataSource("myConfig");
	private static DataSource dataSource = new ComboPooledDataSource();

	/**
	 * 它为null表示没有事务<br/>
	 * 它不为null表示有事务 当开启事务时，需要给它赋值 当结束事务时，需要给它赋值为null</br>
	 * 并且在开启事务时，多个方法共享这个Connection
	 */
	private static ThreadLocal<Connection> tLocolConn = new ThreadLocal<>();

	/**
	 * 获取数据源
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 获取连接<br/>
	 * 当有事务时，返回事务使用的连接<br/>
	 * 当没有事务时，通过连接池重新申请一个连接并返回
	 * 
	 * @return 有事务，事务连接；无事务，连接池连接
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		// 获取本地线程中的事务连接
		Connection conn = tLocolConn.get();
		if (conn == null) {
			// 重新申请一个连接
			conn = dataSource.getConnection();
		}
		return conn;
	}

	/**
	 * 开启事务，连接可以通过getConnection()方法获得
	 * 
	 * @throws SQLException
	 */
	public static void beginTransaction() throws SQLException {
		// 获取本地线程中的事务连接
		Connection conn = tLocolConn.get();
		if (conn != null) {
			throw new SQLException("已经开启事务，请勿重复开启！");
		}
		// 重新申请一个连接
		conn = dataSource.getConnection();
		// 开启事务
		conn.setAutoCommit(false);
		// 加入本地线程
		tLocolConn.set(conn);
	}

	/**
	 * 提交事务，移除本地线程中的事务连接
	 * 
	 * @throws SQLException
	 */
	public static void commitTransaction() throws SQLException {
		// 获取本地线程中的事务连接
		Connection conn = tLocolConn.get();
		if (conn == null) {
			throw new SQLException("没有事务，请勿提交！");
		}
		// 提交事务
		conn.commit();
		// 连接释放回线程池
		conn.close();
		// 移除本地线程中的事务连接
		tLocolConn.remove();
	}

	/**
	 * 回滚事务，移除本地线程中的事务连接
	 * 
	 * @throws SQLException
	 */
	public static void rollbackTransaction() throws SQLException {
		// 获取本地线程中的事务连接
		Connection conn = tLocolConn.get();
		if (conn == null) {
			throw new SQLException("没有事务，请勿回滚！");
		}
		// 回滚事务
		conn.rollback();
		// 连接释放回线程池
		conn.close();
		// 移除本地线程中的事务连接
		tLocolConn.remove();
	}

	/**
	 * 释放线程池获取的连接，注意！不能释放本地线程中的事务连接
	 * 
	 * @param connection
	 *            线程池获取的连接
	 * @throws SQLException
	 */
	public static void releaseConnection(Connection connection) throws SQLException {
		// 获取本地线程中的事务连接
		Connection conn = tLocolConn.get();
		// 释放线程不是本地线程中的事务连接
		// 不为空，没有关闭过
		if (conn != connection && connection != null && !connection.isClosed()) {
			// 连接释放回线程池
			connection.close();
		}
	}
}
