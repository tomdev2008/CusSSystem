package com.kfxt.Service;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class test {
//	public static void main(String[] args) {
//		System.out.println("内存使用"+new test().mx()*100);
//		System.out.println("cpu使用"+new test().cpu());
//	}
//	// 获取内存使用率
//	private double mx() {
//		Sigar sigar = new Sigar();
//		try {
//			Mem mem = sigar.getMem();
//			double gt = mem.getTotal() / 1024L;
//			double gu = mem.getUsed() / 1024L;
//			double arm = gu / gt;
//			return arm;
//		} catch (SigarException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return 0;
//	}
//
//	// 获得cpu使用率
//	private double cpu() {
//		Sigar sigar = new Sigar();
//		CpuInfo infos[] = null;
//		try {
//			infos = sigar.getCpuInfoList();
//		} catch (SigarException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		CpuPerc cpuList[] = null;
//		try {
//			cpuList = sigar.getCpuPercList();
//		} catch (SigarException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		double sum = 0;
//		for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
//			sum += printCpuPerc(cpuList[i]);
//		}
//		return sum / 4 * 100;
//	}
//
//	private double printCpuPerc(CpuPerc cpu) {
//		return cpu.getCombined();
//	}
}
