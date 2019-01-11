package com.kfxt.File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadText {
	private String path = "D:\\";

	/**
	 * 把消息按行读出
	 * 
	 * @return 返回按行读出的list
	 */
	public List<String> readRow() {
		// 使用ArrayList来存储每行读取到的字符串
		List<String> arrayList = new ArrayList<>();
		try {
			String filename = path + "wdxx.txt";
			File file = new File(filename);
			InputStreamReader input = new InputStreamReader(new FileInputStream(file), "utf-8");
			BufferedReader bf = new BufferedReader(input);
			// 按行读取字符串
			String str;
			while ((str = bf.readLine()) != null) {
				arrayList.add(str);
			}
			bf.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 返回数组
		return arrayList;
	}

	/**
	 * 按用户删除未读消息
	 * 
	 * @param acceptname
	 */
	public void delRow(String acceptname) {
		BufferedReader bis = null;
		FileWriter bos = null;
		String s = null;
		// 设置删除的正则表达式
		String ch = acceptname + "(.*)";
		String filepath = path + "wdxx.txt";
		String tmp = path + "tmp.txt";
		try {
			bis = new BufferedReader(new FileReader(filepath));
			bos = new FileWriter(tmp);
			while (null != (s = bis.readLine())) {
				// 使用正则匹配删除
				s = s.replaceAll(ch, "");
				if (!s.equals("")) {
					s = s + "\r\n";
					bos.write(s);
				}
			}
			bos.close();
			bis.close();
			// 删除老的文件
			File file = new File(filepath);
			file.delete();
			// 重命名新生成的文件
			File oldfile = new File(tmp);
			File newfile = new File(filepath);
			oldfile.renameTo(newfile);
		} catch (FileNotFoundException e) {
			System.out.println("未找到文件\n");
		} catch (IOException ee) {
			System.out.println("io错误");
		}
	}
	/**
	 * 按行读取
	 * @param file文件对象
	 * @param numRead最后多少行
	 * @return
	 */
	public static List<String> readLastNLine(File file, long numRead) {
		List<String> result = new ArrayList<String>(); // 定义结果集
		try {
			long lines = Files.lines(Paths.get(file.getPath())).count();// 获取文件行数
			long count = 0;// 统计行数
			InputStreamReader input = new InputStreamReader(new FileInputStream(file), "utf-8");
			BufferedReader bf = new BufferedReader(input);
			// 按行读取字符串
			String str;
			while ((str = bf.readLine()) != null) {
				count++;
				if (count>lines-numRead) {
					result.add(str);
				}
			}
			bf.close();
			input.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;

	}

}
