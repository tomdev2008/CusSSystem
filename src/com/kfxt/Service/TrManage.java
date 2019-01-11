package com.kfxt.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 线程管理，对websocket线程管理
public class TrManage {
	// 存放websocket连接套接字（存放在线用户对应的session）
	// ConcurrentHashMap是线程安全的
	// hashmap是线程不安全的
	private static Map<String, ChatServer> room = new ConcurrentHashMap<>();
	// 把通信对象存入map集合中
	public static void addClientThread(String sendname,ChatServer cs) {
		room.put(sendname, cs);
	}
	// 根据传入的值获取对应的对象
	public static ChatServer getClientThread(String acceptname) {
		return room.get(acceptname);
	}
	// 连接断开时，删除该用户
	public static void delChat(String sendname) {
		room.remove(sendname);
	}
	// 在线人数
	public static int getsum() {
		return room.size();
	}
}
