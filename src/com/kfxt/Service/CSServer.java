package com.kfxt.Service;
// �ͷ�����������

import java.sql.SQLException;
import java.util.List;

import com.kfxt.bean.CuSBean;
import com.kfxt.dao.CuSDao;

import net.sf.json.JSONObject;

public class CSServer {
	// ��ȡ����
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
	// ɾ���ͷ�
	/**
	 * 
	 * @param id �ͷ���Ӧ��id
	 * @return �����Ƿ�ɾ���ɹ�
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
	// �����ͷ�
	/**
	 * 
	 * @param data �ͷ�����
	 * @return �����Ƿ������ɹ�
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
