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
 * @ServerEndpoint 注解为websocket的ws://协议访问地址
 * @PathParam 用于对参数值进行解析，还有一种传值方式
 * @OnOpen 新用户接入调用函数
 * @OnClose 用户断开调用函数
 * @OnMessage 用户在两次确认完成后向服务器发送数据调用的函数
 * @author 军
 *
 */
@ServerEndpoint(value = "/websocket/{param}",configurator=GetHttpSessionConfigurator.class)
public class ChatServer {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 用来记录当前在线连接数
	private static int onlineCount;
	// 谁发送的
	private String sendname = "";
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 用户接入 param 路径映射参数 session 当前连接对象的session OnOpen为注解，表示用户接入
	 */
	@OnOpen
	public void onOpen(@PathParam("param") String param, Session session,EndpointConfig config) {
//		HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
//		if (httpSession.getAttribute("loginUser")!=null) {
			// 将连接的session取出,赋值给本自定义的session
			this.session = session;
			// 获取参数，此参数是该链接的一个用户标识符
			sendname = param;
			// 把当前对象传入
			TrManage.addClientThread(sendname, this);
			// 判断此用户是否有历史未读消息
			CuSNoOnline csn = new CuSNoOnline();
			csn.getHisMsg(sendname);
			// 用户数+1，输出日志
			userAdd();
//		}else {
//			System.out.println("客户端没有登录");
//		}
	}
	
	// 用户断开
	@OnClose
	public void onClose(Session session) {
		userSub();
	}
	
	/*
	 * 此处还有个可选参数session
	 * 因为session之前已经存入map中这里不再需要
	 * 字符串流
	 */
	// 用户发送消息到服务器
	@OnMessage
	public void onMessage(String message) {
		// 把用户发来的消息解析为JSON对象
		JSONObject obj = JSONObject.fromObject(message);
		// 解析出接收对象
		String dx = obj.getString("acceptname");
		if (TrManage.getClientThread(dx)!=null) {
			// 把消息直接写入json文件中，当需要查询历史消息时可以调用
			// 把消息发送给客服
			TrManage.getClientThread(dx).session.getAsyncRemote().sendText(obj.toString());
			// 把已发送的信息写入日志中
			infoOut(obj);
		}else {
			// 当消息接收方不在线时，把消息存入json中
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
	 * 错误处理
	 * @param session 用户session
	 * @param error
	 */
	@OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
	
	/**
	 * 用户数加1，并输出日志到文件中
	 */
	public void userAdd() {
		onlineCount++; // 在线数加
		String time = df.format(new Date());
		String msg = time+"--"+"有新连接加入！新用户：" + sendname + ",当前在线人数为" + onlineCount;
		WriteText.isWriteFile("log", msg);// 写入日志
		System.out.println(msg);
	}
	/**
	 * 用户数减1，输出日志
	 */
	public void userSub() {
		if (onlineCount>0) {
			onlineCount--; // 在线数减
			// 删除该用户的连接及session
			TrManage.delChat(sendname);
			String time = df.format(new Date());
			String msg = time+"--"+"有一连接关闭！当前在线人数为" + onlineCount;
			WriteText.isWriteFile("log", msg);
			System.out.println(msg);
		}
	}
	/**
	 * 把用户传来的信息输出到日志
	 */
	public void infoOut(JSONObject obj) {
		String time = df.format(new Date());
		String msg = time+"--"+"新消息传入：\r\n"+"        发送用户为："+sendname+"\r\n"
				+ "        发送来的消息为："+obj.toString()+"\r\n"+
				"        发送给"+obj.getString("acceptname");
		WriteText.isWriteFile("log", msg);
		System.out.println(msg); //打印发送来的信息
	}
	/**
	 * 消息未发送出去
	 * @param obj	当消息未发送出去时
	 */
	public void infoNotOut(JSONObject obj) {
		String time = df.format(new Date());
		String msg = time+"--"+"对方未在线，消息未发送：\r\n"+"        发送用户为："+sendname+"\r\n"
				+ "        发送来的消息为："+obj.toString()+"\r\n"+
				"        发送给"+obj.getString("acceptname");
		WriteText.isWriteFile("log", msg);
	}
}
