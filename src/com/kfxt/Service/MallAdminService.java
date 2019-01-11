package com.kfxt.Service;
//商城管理员获取客服

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kfxt.bean.CuSBean;
import com.kfxt.dao.CuSDao;

public class MallAdminService {
	/**
	 * 管理员获取客服列表and普通用户获取客服列表
	 * @return
	 */
	public List<String> mallAdminGetCS(){
		//获取数据
		List<String> data = new ArrayList<String>();
		try {
			List<CuSBean> list = new CuSDao().findAll();
			for (int i = 0; i < list.size(); i++) {
				// 把数据存入list中
				data.add(list.get(i).getKfid());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;// 返回数据
	}
}
