package com.kfxt.dao;
// �ͷ������

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kfxt.bean.CuSBean;
import com.kfxt.util.c3p0;

public class CuSDao {
	/**
	 * ��ѯ�ͷ��б�
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<CuSBean> findAll() throws SQLException {
		// ��ȡ���ݿ����ӳأ���������
		QueryRunner qr = new QueryRunner(c3p0.ds);
		String sql = "select * from CuS";
		List<CuSBean> list = qr.query(sql, new BeanListHandler<CuSBean>(CuSBean.class));
		return list;
	}

	/**
	 * �����ͷ�
	 * 
	 * @param data
	 * @return
	 * @throws SQLException
	 */
	public boolean addUser(CuSBean data) throws SQLException {
		// ��ȡ���ݿ����ӳأ���������
		QueryRunner qr = new QueryRunner(c3p0.ds);
		String sql = "insert into CuS (kfid,rsttime) values (?, ?)";
		int result = qr.update(sql, data.getKfid(), data.getRsttime());
		if (result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * ɾ���ͷ�
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
