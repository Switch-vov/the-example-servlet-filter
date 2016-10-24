package com.pc.user.domain;

import java.io.Serializable;

/**
 * UserBean
 * 
 * @author Switch
 * @data 2016年10月24日
 * @version V1.0
 */
public class User implements Serializable {
	private String uid;
	private String username;
	private String password;

	public User() {
		super();
	}

	public User(String uid, String username, String password) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + "]";
	}

}
