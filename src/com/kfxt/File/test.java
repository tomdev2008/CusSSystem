package com.kfxt.File;

import java.io.File;
import java.util.List;

public class test {
	public static void main(String[] args) {
		List<String> data = ReadText.readLastNLine(new File("D:\\log.txt"), 5L);
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
	}
}
