package com.kfxt.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// �̹߳�����websocket�̹߳���
public class TrManage {
	// ���websocket�����׽��֣���������û���Ӧ��session��
	// ConcurrentHashMap���̰߳�ȫ��
	// hashmap���̲߳���ȫ��
	private static Map<String, ChatServer> room = new ConcurrentHashMap<>();
	// ��ͨ�Ŷ������map������
	public static void addClientThread(String sendname,ChatServer cs) {
		room.put(sendname, cs);
	}
	// ���ݴ����ֵ��ȡ��Ӧ�Ķ���
	public static ChatServer getClientThread(String acceptname) {
		return room.get(acceptname);
	}
	// ���ӶϿ�ʱ��ɾ�����û�
	public static void delChat(String sendname) {
		room.remove(sendname);
	}
	// ��������
	public static int getsum() {
		return room.size();
	}
}
