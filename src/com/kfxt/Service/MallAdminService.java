package com.kfxt.Service;
//�̳ǹ���Ա��ȡ�ͷ�

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kfxt.bean.CuSBean;
import com.kfxt.dao.CuSDao;

public class MallAdminService {
	/**
	 * ����Ա��ȡ�ͷ��б�and��ͨ�û���ȡ�ͷ��б�
	 * @return
	 */
	public List<String> mallAdminGetCS(){
		//��ȡ����
		List<String> data = new ArrayList<String>();
		try {
			List<CuSBean> list = new CuSDao().findAll();
			for (int i = 0; i < list.size(); i++) {
				// �����ݴ���list��
				data.add(list.get(i).getKfid());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;// ��������
	}
}
