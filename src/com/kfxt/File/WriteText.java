package com.kfxt.File;

import java.io.*;

public class WriteText {
	//�����ļ�·��
    private static String path = "D:\\";
    
    //�ļ�·��+����
    private static String filenameTemp;
    
    // �����ļ�
    public static void isWriteFile(String fileName,String filecontent){
        filenameTemp = path+fileName+".txt";//�ļ�·��+����+�ļ�����
        File file = new File(filenameTemp);
        FileOutputStream fos = null;
        try {
            //����ļ������ڣ��򴴽��µ��ļ�
            if(!file.exists()){
                file.createNewFile();
                fos = new FileOutputStream(file);//�״�д���ȡ
            }else {
            	//����ļ��Ѵ��ڣ���ô�����ļ�ĩβ׷��д��
            	fos = new FileOutputStream(file,true);//���ﹹ�췽������һ������true,��ʾ���ļ�ĩβ׷��д��
			}
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");//ָ����UTF-8��ʽд���ļ�
            osw.write(filecontent+"\r\n");
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
