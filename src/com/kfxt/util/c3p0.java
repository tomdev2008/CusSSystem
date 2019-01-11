package com.kfxt.util;

import java.beans.PropertyVetoException;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

// 数据库连接池技术-采用c3p0技术
public class c3p0 {
	// 数据源对象--可以从数据源获取连接对象connection
	public static DataSource ds = null;
	// 给数据源赋值--通过c3p0数据连接池
	static {
		// 获取数据连接池对象
		ComboPooledDataSource bds = new ComboPooledDataSource();
		// 设置参数
		// 设置数据库驱动名：sqlserver/mysql/oracle
		try {
			bds.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 设置数据库的位置：协议，ip地址，端口，数据库名
		bds.setJdbcUrl("jdbc:sqlserver://localhost:1433;databaseName=CSDB");
		// 设置登录的用户名
		bds.setUser("sa");
		// 设置登录密码
		bds.setPassword("tj584230");
		// 设置连接池初始连接数量
		bds.setInitialPoolSize(5);
		// 设置连接池的最大连接数量
		bds.setMaxPoolSize(10);
		ds = bds;
	}

	public static void main(String[] args) throws SQLException {
		// 测试连接池_获取连接对象
		java.sql.Connection conn = ds.getConnection();
		DatabaseMetaData md = conn.getMetaData();
		System.out.println(md.getDriverName());
		System.out.println(md.getURL());
		System.out.println(md.getUserName());
	}

}
