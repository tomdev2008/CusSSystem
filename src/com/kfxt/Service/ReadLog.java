package com.kfxt.Service;
// 获取日志

import java.io.File;
import java.util.List;

import com.kfxt.File.ReadText;

import net.sf.json.JSONObject;

public class ReadLog {
	public JSONObject getLoglist(){
		List<String> data = ReadText.readLastNLine(new File("D:\\log.txt"), 20L);
		JSONObject obj = new JSONObject();
		String tmp = "";
		for (int i = 0; i < data.size(); i++) {
			tmp += "<span class=\"am-text-success\">"+data.get(i)+"</span><br/>";
		}
		obj.put("log", tmp);
		return obj;
	}
}
