package com.kfxt.dao;
// ����Ա������

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kfxt.bean.AdminBean;
import com.kfxt.util.c3p0;

public class AdminDao {
	/**
	 * ��ѯ����Ա
	 * @param username ����Ա��¼��
	 * @return ��������
	 * @throws SQLException 
	 */
	public AdminBean finduserpass(String username,String userpass) throws SQLException{
		QueryRunner qr = new QueryRunner(c3p0.ds);
		String sql = "select userpass from Admin where username = ? and userpass = ?";
		AdminBean data = qr.query(sql, new BeanHandler<AdminBean>(AdminBean.class),username,userpass);
		return data;
	}
	/**
	 * �޸Ĺ���Ա
	 * 
	 * @return �������ݿ������й���Ա����
	 * @throws SQLException
	 */
	public List<AdminBean> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0.ds);
		String sql = "select * from Admin";
		List<AdminBean> list = qr.query(sql, new BeanListHandler<AdminBean>(AdminBean.class));
		return list;
	}

	/**
	 * �޸Ĺ���Ա
	 * 
	 * @param data
	 *            ����
	 * @return ����״̬
	 * @throws SQLException
	 */
	public boolean modifyUser(AdminBean data) throws SQLException {
		// ��ȡ����
		QueryRunner qr = new QueryRunner(c3p0.ds);
		String sql = "update Admin set username = ?, userpass = ? where userid = ?";
		int result = qr.update(sql, data.getUsername(), data.getUserpass(), data.getUserid());// ִ��sql���
		if (result > 0) {// ���޸ĵ����ݴ���1��ʱ
			return true;
		}
		return false;
	}

	/**
	 * ɾ������Ա
	 * 
	 * @param id
	 *            ����ԱΨһid
	 * @return �����Ƿ�ɹ�
	 * @throws SQLException
	 */
	public boolean delUser(String id) throws SQLException {
		if (findcount()>1) {// ����1������Ա��ɾ��
			// ��ȡ����
			QueryRunner qr = new QueryRunner(c3p0.ds);
			String sql = "delete from Admin where userid = ?";
			int result = qr.update(sql, id);
			if (result > 0) {
				return true;// ɾ���ɹ�
			}
		}
		return false;// ɾ��ʧ��
	}

	/**
	 * ��������Ա
	 * 
	 * @param data�¹���Ա����
	 * @return �����Ƿ�ɹ�
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
	 * ��ѯ����Ա����������
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
