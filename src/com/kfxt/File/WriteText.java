package com.kfxt.File;

import java.io.*;

public class WriteText {
	//生成文件路径
    private static String path = "D:\\";
    
    //文件路径+名称
    private static String filenameTemp;
    
    // 创建文件
    public static void isWriteFile(String fileName,String filecontent){
        filenameTemp = path+fileName+".txt";//文件路径+名称+文件类型
        File file = new File(filenameTemp);
        FileOutputStream fos = null;
        try {
            //如果文件不存在，则创建新的文件
            if(!file.exists()){
                file.createNewFile();
                fos = new FileOutputStream(file);//首次写入获取
            }else {
            	//如果文件已存在，那么就在文件末尾追加写入
            	fos = new FileOutputStream(file,true);//这里构造方法多了一个参数true,表示在文件末尾追加写入
			}
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");//指定以UTF-8格式写入文件
            osw.write(filecontent+"\r\n");
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
