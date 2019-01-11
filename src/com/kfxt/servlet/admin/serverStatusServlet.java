package com.kfxt.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kfxt.Service.GetRamCpu;
import com.kfxt.Service.ReadLog;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class serverStatus
 */
@WebServlet("/kfxt/admin/serverStatus")
public class serverStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serverStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����ʴ�servletʱ�������̷߳�������
//		GetRamCpu gr = new GetRamCpu();
//		Thread tmp= new Thread(gr);
//		tmp.start();
//		response.setStatus(200);
		// ��ȡ����
		// �ͻ�����ѯ
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		GetRamCpu gr = new GetRamCpu();
		JSONObject msg = gr.getstatus();
		out.println(msg.toString());
        out.flush();
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
