package com.kfxt.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import net.sf.json.JSONObject;

// 定时获取cpu、ram使用率、在线人数
public class GetRamCpu {
	private static SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
	Properties props = System.getProperties();
	private double cpuRation;// cpu使用率
	private double ramRation;// ram使用率
	private int onineSum;// 在线人数
	private String time;// 时间
	private String osName = props.getProperty("os.name");// 操作系统名称
	// @Override
	// public void run() {
	// while (true) {
	// if(GetRamCpu.interrupted()){
	// // 当线程被种断标记，返回终止
	// return;
	// }
	// // 循环获取
	// time = df.format(new Date());
	// cpuRation = (int) (mx()*100);
	// ramRation = (int) (cpu()*100);
	// onineSum = TrManage.getsum();
	// JSONObject flag = new JSONObject();
	// flag.put("time", time);
	// flag.put("cpuRation", cpuRation);
	// flag.put("ramRation", ramRation);
	// flag.put("onineSum", onineSum);
	// TrManage.getClientThread("fwq").onMessage(flag.toString());
	// try {
	// Thread.sleep(5000);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }

	/**
	 * 采用ajax轮询方式
	 */
	public JSONObject getstatus() {
		time = df.format(new Date());
		cpuRation = (cpu());
		ramRation = (mx() * 100);
		onineSum = TrManage.getsum();
		JSONObject flag = new JSONObject();
		flag.put("time", time);
		flag.put("cpuRation", cpuRation);
		flag.put("ramRation", ramRation);
		flag.put("onineSum", onineSum);
		flag.put("osName", osName);
		return flag;
	}

	// 获取内存使用率
	private double mx() {
		Sigar sigar = new Sigar();
		try {
			Mem mem = sigar.getMem();
			double gt = mem.getTotal() / 1024L;
			double gu = mem.getUsed() / 1024L;
			double arm = gu / gt;
			return arm;
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// 获得cpu使用率
	private double cpu() {
		Sigar sigar = new Sigar();
		CpuInfo infos[] = null;
		try {
			infos = sigar.getCpuInfoList();
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CpuPerc cpuList[] = null;
		try {
			cpuList = sigar.getCpuPercList();
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double sum = 0;
		for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
			sum += printCpuPerc(cpuList[i]);
		}
		return sum / 4 * 1000;
	}

	private double printCpuPerc(CpuPerc cpu) {
		return cpu.getCombined();
	}
}
