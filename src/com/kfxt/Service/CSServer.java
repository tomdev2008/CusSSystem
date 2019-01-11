package com.kfxt.Service;
// 客服表操作服务层

import java.sql.SQLException;
import java.util.List;

import com.kfxt.bean.CuSBean;
import com.kfxt.dao.CuSDao;

import net.sf.json.JSONObject;

public class CSServer {
	// 获取数据
	public JSONObject findAllService(){
		try {
			List<CuSBean> list = new CuSDao().findAll();
			JSONObject data = new JSONObject();
			String sum ="[";
			for (int i = 0; i < list.size(); i++) {
				String msg = "{\"id\":\""+list.get(i).getKfid()+"\",\"time\":\""+list.get(i).getRsttime()+"\"}";
				if (i==list.size()-1) {
					sum +=msg+"]";
				}else {
					sum +=msg+",";
				}
			}
			data.put("data", sum);
			data.put("le", list.size());
			return data;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	// 删除客服
	/**
	 * 
	 * @param id 客服对应的id
	 * @return 返回是否删除成功
	 */
	public boolean delUserService(String id) {
		boolean flag = false;
		try {
			flag = new CuSDao().delUser(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	// 新增客服
	/**
	 * 
	 * @param data 客服数据
	 * @return 返回是否新增成功
	 */
	public boolean addUserService(CuSBean data) {
		boolean flag = false;
		try {
			 flag = new CuSDao().addUser(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
}
