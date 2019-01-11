package com.kfxt.dao;
// 客服表操作

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kfxt.bean.CuSBean;
import com.kfxt.util.c3p0;

public class CuSDao {
	/**
	 * 查询客服列表
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<CuSBean> findAll() throws SQLException {
		// 获取数据库连接池，创建连接
		QueryRunner qr = new QueryRunner(c3p0.ds);
		String sql = "select * from CuS";
		List<CuSBean> list = qr.query(sql, new BeanListHandler<CuSBean>(CuSBean.class));
		return list;
	}

	/**
	 * 新增客服
	 * 
	 * @param data
	 * @return
	 * @throws SQLException
	 */
	public boolean addUser(CuSBean data) throws SQLException {
		// 获取数据库连接池，创建连接
		QueryRunner qr = new QueryRunner(c3p0.ds);
		String sql = "insert into CuS (kfid,rsttime) values (?, ?)";
		int result = qr.update(sql, data.getKfid(), data.getRsttime());
		if (result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 删除客服
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean delUser(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0.ds);
		String sql = "delete from CuS where kfid = ?";
		int result = qr.update(sql, id);
		if (result > 0) {
			return true;
		}
		return false;
	}
}
