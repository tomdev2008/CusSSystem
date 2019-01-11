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
	 * ����Ϣ���ж���
	 * 
	 * @return ���ذ��ж�����list
	 */
	public List<String> readRow() {
		// ʹ��ArrayList���洢ÿ�ж�ȡ�����ַ���
		List<String> arrayList = new ArrayList<>();
		try {
			String filename = path + "wdxx.txt";
			File file = new File(filename);
			InputStreamReader input = new InputStreamReader(new FileInputStream(file), "utf-8");
			BufferedReader bf = new BufferedReader(input);
			// ���ж�ȡ�ַ���
			String str;
			while ((str = bf.readLine()) != null) {
				arrayList.add(str);
			}
			bf.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ��������
		return arrayList;
	}

	/**
	 * ���û�ɾ��δ����Ϣ
	 * 
	 * @param acceptname
	 */
	public void delRow(String acceptname) {
		BufferedReader bis = null;
		FileWriter bos = null;
		String s = null;
		// ����ɾ����������ʽ
		String ch = acceptname + "(.*)";
		String filepath = path + "wdxx.txt";
		String tmp = path + "tmp.txt";
		try {
			bis = new BufferedReader(new FileReader(filepath));
			bos = new FileWriter(tmp);
			while (null != (s = bis.readLine())) {
				// ʹ������ƥ��ɾ��
				s = s.replaceAll(ch, "");
				if (!s.equals("")) {
					s = s + "\r\n";
					bos.write(s);
				}
			}
			bos.close();
			bis.close();
			// ɾ���ϵ��ļ�
			File file = new File(filepath);
			file.delete();
			// �����������ɵ��ļ�
			File oldfile = new File(tmp);
			File newfile = new File(filepath);
			oldfile.renameTo(newfile);
		} catch (FileNotFoundException e) {
			System.out.println("δ�ҵ��ļ�\n");
		} catch (IOException ee) {
			System.out.println("io����");
		}
	}
	/**
	 * ���ж�ȡ
	 * @param file�ļ�����
	 * @param numRead��������
	 * @return
	 */
	public static List<String> readLastNLine(File file, long numRead) {
		List<String> result = new ArrayList<String>(); // ��������
		try {
			long lines = Files.lines(Paths.get(file.getPath())).count();// ��ȡ�ļ�����
			long count = 0;// ͳ������
			InputStreamReader input = new InputStreamReader(new FileInputStream(file), "utf-8");
			BufferedReader bf = new BufferedReader(input);
			// ���ж�ȡ�ַ���
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
