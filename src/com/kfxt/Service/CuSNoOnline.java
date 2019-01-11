package com.kfxt.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kfxt.File.ReadText;
import com.kfxt.File.WriteText;

import net.sf.json.JSONObject;

// �������Ͷ�������ʱ���ô���
public class CuSNoOnline {
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * ����Ϣ���뱾��wdxx.txt�ļ���
	 * 
	 * @param data
	 *            ���͹�����json��ʽ��Ϣ
	 * @param acceptname
	 *            ��Ϣ���շ�
	 */
	public void setMsgJson(JSONObject data, String acceptname) {
		// ������Ϣ��ʽ
		// �������û�������Ϣ���壬-$-$-$-�ָ��
		String newmsg = acceptname + "----" + data.toString();
		WriteText.isWriteFile("wdxx", newmsg);
	}

	/**
	 * �������û�����ʱ�����ô˷������鿴���û��Ƿ���Ϊ�鿴��Ϣ �˷���û�з���ֵ�����û���δ����Ϣʱ���Զ�����websocket�����������򲻼���
	 * 
	 * @param acceptname
	 *            ��ǰ�����ߵ��û�
	 */
	public void getHisMsg(String acceptname) {
		// ��ѯ�Ƿ�����ʷ��Ϣ���������
		List<String> msg = getMsgJson(acceptname);
		if (msg != null) {
			// ��ȡ�û���websocket����
			ChatServer websocket = TrManage.getClientThread(acceptname);
			// �����û�����ʷδ����Ϣʱ
			for (int i = 0; i < msg.size(); i++) {
				// ѭ������Ϣ����
				websocket.onMessage(msg.get(i));
			}
		} else {
			String time = df.format(new Date());
			String flag = time + "��" + acceptname + "�û�û����ʷδ����Ϣ";
			WriteText.isWriteFile("log", flag);
		}
	}
	
	/**
	 *  ���ļ��ж�ȡ��Ϣ,��ȡָ�����û�δ����Ϣ
	 * @param acceptname
	 *            ��Ϣ���շ�
	 * @return
	 */
	public List<String> getMsgJson(String acceptname) {
		// ��ȡ����
		List<String> data = new ReadText().readRow();
		List<String> list_data = new ArrayList<String>();
		for (int i = 0; i < data.size(); i++) {
			String str = data.get(i);
			String arr[] = str.split("----");
			String flag = arr[0];
//			System.out.println(flag);
			if (flag.equals(acceptname)) {
				// ����Ϣ��ӵ�list��ȥ
				list_data.add(arr[1]);
			}
		}
		if (null == list_data || list_data.size() ==0) {
			return null;
		}else {
			// �����ݶ�ȡ������ɾ����Ϣ
			ReadText rt = new ReadText();
			rt.delRow(acceptname);
			return list_data;
		}
	}
}
