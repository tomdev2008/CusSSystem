package com.kfxt.bean;

//管理员bean
public class AdminBean {
	private String userid;// 管理员id
	private String username;// 管理员登录名
	private String userpass;// 管理员密码
	private String rsttime;// 管理员注册时间

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getRsttime() {
		return rsttime;
	}

	public void setRsttime(String rsttime) {
		this.rsttime = rsttime;
	}

}
