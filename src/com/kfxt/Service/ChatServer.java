package com.kfxt.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.kfxt.File.WriteText;

import net.sf.json.JSONObject;

/**
 * @ServerEndpoint ע��Ϊwebsocket��ws://Э����ʵ�ַ
 * @PathParam ���ڶԲ���ֵ���н���������һ�ִ�ֵ��ʽ
 * @OnOpen ���û�������ú���
 * @OnClose �û��Ͽ����ú���
 * @OnMessage �û�������ȷ����ɺ���������������ݵ��õĺ���
 * @author ��
 *
 */
@ServerEndpoint(value = "/websocket/{param}",configurator=GetHttpSessionConfigurator.class)
public class ChatServer {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// ������¼��ǰ����������
	private static int onlineCount;
	// ˭���͵�
	private String sendname = "";
	// ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
	private Session session;

	/**
	 * �û����� param ·��ӳ����� session ��ǰ���Ӷ����session OnOpenΪע�⣬��ʾ�û�����
	 */
	@OnOpen
	public void onOpen(@PathParam("param") String param, Session session,EndpointConfig config) {
//		HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
//		if (httpSession.getAttribute("loginUser")!=null) {
			// �����ӵ�sessionȡ��,��ֵ�����Զ����session
			this.session = session;
			// ��ȡ�������˲����Ǹ����ӵ�һ���û���ʶ��
			sendname = param;
			// �ѵ�ǰ������
			TrManage.addClientThread(sendname, this);
			// �жϴ��û��Ƿ�����ʷδ����Ϣ
			CuSNoOnline csn = new CuSNoOnline();
			csn.getHisMsg(sendname);
			// �û���+1�������־
			userAdd();
//		}else {
//			System.out.println("�ͻ���û�е�¼");
//		}
	}
	
	// �û��Ͽ�
	@OnClose
	public void onClose(Session session) {
		userSub();
	}
	
	/*
	 * �˴����и���ѡ����session
	 * ��Ϊsession֮ǰ�Ѿ�����map�����ﲻ����Ҫ
	 * �ַ�����
	 */
	// �û�������Ϣ��������
	@OnMessage
	public void onMessage(String message) {
		// ���û���������Ϣ����ΪJSON����
		JSONObject obj = JSONObject.fromObject(message);
		// ���������ն���
		String dx = obj.getString("acceptname");
		if (TrManage.getClientThread(dx)!=null) {
			// ����Ϣֱ��д��json�ļ��У�����Ҫ��ѯ��ʷ��Ϣʱ���Ե���
			// ����Ϣ���͸��ͷ�
			TrManage.getClientThread(dx).session.getAsyncRemote().sendText(obj.toString());
			// ���ѷ��͵���Ϣд����־��
			infoOut(obj);
		}else {
			// ����Ϣ���շ�������ʱ������Ϣ����json��
			CuSNoOnline cso = new CuSNoOnline();
			cso.setMsgJson(obj, dx);
			infoNotOut(obj);
		}
	}
	
//	@OnMessage
//	public void onMessage(byte[] message) {
//		
//	}
	
	/**
	 * ������
	 * @param session �û�session
	 * @param error
	 */
	@OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
	
	/**
	 * �û�����1���������־���ļ���
	 */
	public void userAdd() {
		onlineCount++; // ��������
		String time = df.format(new Date());
		String msg = time+"--"+"�������Ӽ��룡���û���" + sendname + ",��ǰ��������Ϊ" + onlineCount;
		WriteText.isWriteFile("log", msg);// д����־
		System.out.println(msg);
	}
	/**
	 * �û�����1�������־
	 */
	public void userSub() {
		if (onlineCount>0) {
			onlineCount--; // ��������
			// ɾ�����û������Ӽ�session
			TrManage.delChat(sendname);
			String time = df.format(new Date());
			String msg = time+"--"+"��һ���ӹرգ���ǰ��������Ϊ" + onlineCount;
			WriteText.isWriteFile("log", msg);
			System.out.println(msg);
		}
	}
	/**
	 * ���û���������Ϣ�������־
	 */
	public void infoOut(JSONObject obj) {
		String time = df.format(new Date());
		String msg = time+"--"+"����Ϣ���룺\r\n"+"        �����û�Ϊ��"+sendname+"\r\n"
				+ "        ����������ϢΪ��"+obj.toString()+"\r\n"+
				"        ���͸�"+obj.getString("acceptname");
		WriteText.isWriteFile("log", msg);
		System.out.println(msg); //��ӡ����������Ϣ
	}
	/**
	 * ��Ϣδ���ͳ�ȥ
	 * @param obj	����Ϣδ���ͳ�ȥʱ
	 */
	public void infoNotOut(JSONObject obj) {
		String time = df.format(new Date());
		String msg = time+"--"+"�Է�δ���ߣ���Ϣδ���ͣ�\r\n"+"        �����û�Ϊ��"+sendname+"\r\n"
				+ "        ����������ϢΪ��"+obj.toString()+"\r\n"+
				"        ���͸�"+obj.getString("acceptname");
		WriteText.isWriteFile("log", msg);
	}
}
