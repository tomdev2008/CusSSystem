package com.kfxt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//ʹ��MD5��������м���
public class MD5 {
	public static String getMD5(String data){
		if (data==null||data.length()==0) {
			return "";
		}
		//����ƴ���ַ������ַ�������
		StringBuilder sb=new StringBuilder();
		try {
			//��ȡ��װmd5���ܵĶ���
			MessageDigest instance= MessageDigest.getInstance("md5");
			//��������
			byte[] bs= instance.digest(data.getBytes());	
			//��������ȡ������֮�������
			for(byte b:bs){
				//������ת������16����
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
