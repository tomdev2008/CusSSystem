package com.kfxt.dao;
// 管理员管理类

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kfxt.bean.AdminBean;
import com.kfxt.util.c3p0;

public class AdminDao {
	/**
	 * 查询管理员
	 * @param username 管理员登录名
	 * @return 返回密码
	 * @throws SQLException 
	 */
	public AdminBean finduserpass(String username,String userpass) throws SQLException{
		QueryRunner qr = new QueryRunner(c3p0.ds);
		String sql = "select userpass from Admin where username = ? and userpass = ?";
		AdminBean data = qr.query(sql, new BeanHandler<AdminBean>(AdminBean.class),username,userpass);
		return data;
	}
	/**
	 * 修改管理员
	 * 
	 * @return 返回数据库中所有管理员数据
	 * @throws SQLException
	 */
	public List<AdminBean> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0.ds);
		String sql = "select * from Admin";
		List<AdminBean> list = qr.query(sql, new BeanListHandler<AdminBean>(AdminBean.class));
		return list;
	}

	/**
	 * 修改管理员
	 * 
	 * @param data
	 *            数据
	 * @return 返回状态
	 * @throws SQLException
	 */
	public boolean modifyUser(AdminBean data) throws SQLException {
		// 获取连接
		QueryRunner qr = new QueryRunner(c3p0.ds);
		String sql = "update Admin set username = ?, userpass = ? where userid = ?";
		int result = qr.update(sql, data.getUsername(), data.getUserpass(), data.getUserid());// 执行sql语句
		if (result > 0) {// 当修改的数据大于1条时
			return true;
		}
		return false;
	}

	/**
	 * 删除管理员
	 * 
	 * @param id
	 *            管理员唯一id
	 * @return 返回是否成功
	 * @throws SQLException
	 */
	public boolean delUser(String id) throws SQLException {
		if (findcount()>1) {// 大于1个管理员则删除
			// 获取连接
			QueryRunner qr = new QueryRunner(c3p0.ds);
			String sql = "delete from Admin where userid = ?";
			int result = qr.update(sql, id);
			if (result > 0) {
				return true;// 删除成功
			}
		}
		return false;// 删除失败
	}

	/**
	 * 新增管理员
	 * 
	 * @param data新管理员数据
	 * @return 返回是否成功
	 * @throws SQLException
	 */
	public boolean addUser(AdminBean data) throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0.ds);
		String sql = "insert into Admin (username,userpass,rsttime) values (?, ?, ?)";
		int result = qr.update(sql, data.getUsername(), data.getUserpass(), data.getRsttime());
		if (result > 0) {
			return true;
		}
		return false;
	}
	/**
	 * 查询管理员表数据条数
	 * @return
	 * @throws SQLException 
	 */
	public int findcount() throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0.ds);
		String sql = "select * from Admin";
		List<AdminBean> list = qr.query(sql, new BeanListHandler<AdminBean>(AdminBean.class));
		return list.size();
	}
}
