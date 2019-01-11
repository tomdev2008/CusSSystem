package com.kfxt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//使用MD5对密码进行加密
public class MD5 {
	public static String getMD5(String data){
		if (data==null||data.length()==0) {
			return "";
		}
		//定义拼接字符串的字符缓冲区
		StringBuilder sb=new StringBuilder();
		try {
			//获取封装md5加密的对象
			MessageDigest instance= MessageDigest.getInstance("md5");
			//加密数据
			byte[] bs= instance.digest(data.getBytes());	
			//遍历数组取出加密之后的数据
			for(byte b:bs){
				//将负数转成正数16进制
				int x=b&255;
				if(x>=0&&x<=15){
					sb.append("0");
					sb.append(Integer.toHexString(x));
				}else {
					sb.append(Integer.toHexString(x));
				}
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
