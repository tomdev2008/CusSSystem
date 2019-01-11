package com.kfxt.Service;
// 管理员服务层

import java.sql.SQLException;
import java.util.List;

import com.kfxt.bean.AdminBean;
import com.kfxt.dao.AdminDao;

import net.sf.json.JSONObject;

public class AdminService {
	/**
	 * 对比管理员数据
	 * @param username
	 * @param pass
	 * @return
	 */
	public AdminBean contrast(String username,String userpass) {
		boolean flag = false;
		try {
			AdminBean u = new AdminDao().finduserpass(username,userpass);
			if (u!=null) {
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询管理员列表
	 * @return 返回所有管理员数据的json格式
	 */
	public JSONObject findAllService(){
		try {
			List<AdminBean> list = new AdminDao().findAll();
			JSONObject data = new JSONObject();
			String sum ="[";
			for (int i = 0; i < list.size(); i++) {
				String msg = "{\"id\":\""+list.get(i).getUserid()+"\",\"name\":\""+list.get(i).getUsername()+"\",\"time\":\""+list.get(i).getRsttime()+"\"}";
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
	/**
	 * 新增管理员方法
	 * @param data 新管理员数据
	 * @return 返回是否成功
	 */
	public boolean addUserService(AdminBean data) {
		boolean flag = false;
		try {
			flag = new AdminDao().addUser(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 删除管理员
	 * @param id 被删除的管理员id
	 * @return 返回是否成功
	 */
	public boolean delUserService(String id) {
		boolean flag = false;
		try {
			flag = new AdminDao().delUser(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 修管理员
	 * @param data 修改的数据
	 * @return 返回是否成功
	 */
	public boolean modifyUserService(AdminBean data) {
		boolean flage = false;
		try {
			flage = new AdminDao().modifyUser(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flage;
	}
	
}
