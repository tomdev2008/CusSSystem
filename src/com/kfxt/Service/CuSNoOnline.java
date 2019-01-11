package com.kfxt.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kfxt.File.ReadText;
import com.kfxt.File.WriteText;

import net.sf.json.JSONObject;

// 当被发送对象不在线时调用此类
public class CuSNoOnline {
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 把消息存入本地wdxx.txt文件中
	 * 
	 * @param data
	 *            发送过来的json格式消息
	 * @param acceptname
	 *            消息接收方
	 */
	public void setMsgJson(JSONObject data, String acceptname) {
		// 设置消息格式
		// 被发送用户名、消息主体，-$-$-$-分割符
		String newmsg = acceptname + "----" + data.toString();
		WriteText.isWriteFile("wdxx", newmsg);
	}

	/**
	 * 当有新用户上线时，调用此方法，查看此用户是否有为查看消息 此方法没有返回值，当用户有未读消息时，自动加载websocket发送器，否则不加载
	 * 
	 * @param acceptname
	 *            当前新上线的用户
	 */
	public void getHisMsg(String acceptname) {
		// 查询是否有历史消息，有则接收
		List<String> msg = getMsgJson(acceptname);
		if (msg != null) {
			// 获取用户的websocket对象
			ChatServer websocket = TrManage.getClientThread(acceptname);
			// 当此用户有历史未读消息时
			for (int i = 0; i < msg.size(); i++) {
				// 循环把消息发送
				websocket.onMessage(msg.get(i));
			}
		} else {
			String time = df.format(new Date());
			String flag = time + "此" + acceptname + "用户没有历史未读消息";
			WriteText.isWriteFile("log", flag);
		}
	}
	
	/**
	 *  从文件中读取消息,获取指定的用户未读消息
	 * @param acceptname
	 *            消息接收方
	 * @return
	 */
	public List<String> getMsgJson(String acceptname) {
		// 获取数据
		List<String> data = new ReadText().readRow();
		List<String> list_data = new ArrayList<String>();
		for (int i = 0; i < data.size(); i++) {
			String str = data.get(i);
			String arr[] = str.split("----");
			String flag = arr[0];
//			System.out.println(flag);
			if (flag.equals(acceptname)) {
				// 把消息添加到list中去
				list_data.add(arr[1]);
			}
		}
		if (null == list_data || list_data.size() ==0) {
			return null;
		}else {
			// 把数据读取出来后删除消息
			ReadText rt = new ReadText();
			rt.delRow(acceptname);
			return list_data;
		}
	}
}
