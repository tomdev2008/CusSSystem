package com.kfxt.Service;
// ����Ա�����

import java.sql.SQLException;
import java.util.List;

import com.kfxt.bean.AdminBean;
import com.kfxt.dao.AdminDao;

import net.sf.json.JSONObject;

public class AdminService {
	/**
	 * �Աȹ���Ա����
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
	 * ��ѯ����Ա�б�
	 * @return �������й���Ա���ݵ�json��ʽ
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
	 * ��������Ա����
	 * @param data �¹���Ա����
	 * @return �����Ƿ�ɹ�
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
	 * ɾ������Ա
	 * @param id ��ɾ���Ĺ���Աid
	 * @return �����Ƿ�ɹ�
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
	 * �޹���Ա
	 * @param data �޸ĵ�����
	 * @return �����Ƿ�ɹ�
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
