package com.kfxt.util;

import java.beans.PropertyVetoException;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

// ���ݿ����ӳؼ���-����c3p0����
public class c3p0 {
	// ����Դ����--���Դ�����Դ��ȡ���Ӷ���connection
	public static DataSource ds = null;
	// ������Դ��ֵ--ͨ��c3p0�������ӳ�
	static {
		// ��ȡ�������ӳض���
		ComboPooledDataSource bds = new ComboPooledDataSource();
		// ���ò���
		// �������ݿ���������sqlserver/mysql/oracle
		try {
			bds.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �������ݿ��λ�ã�Э�飬ip��ַ���˿ڣ����ݿ���
		bds.setJdbcUrl("jdbc:sqlserver://localhost:1433;databaseName=CSDB");
		// ���õ�¼���û���
		bds.setUser("sa");
		// ���õ�¼����
		bds.setPassword("tj584230");
		// �������ӳس�ʼ��������
		bds.setInitialPoolSize(5);
		// �������ӳص������������
		bds.setMaxPoolSize(10);
		ds = bds;
	}

	public static void main(String[] args) throws SQLException {
		// �������ӳ�_��ȡ���Ӷ���
		java.sql.Connection conn = ds.getConnection();
		DatabaseMetaData md = conn.getMetaData();
		System.out.println(md.getDriverName());
		System.out.println(md.getURL());
		System.out.println(md.getUserName());
	}

}
