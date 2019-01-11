package com.kfxt.servlet.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class YZMServlet
 */
@WebServlet("/kfxt/YZMServlet")
public class YZMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String[] code = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YZMServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ڷ�����ͼƬ,������Ӧ�����ݵ�����
		response.setContentType("image/jpeg");
		//���ڷ�����ͼƬ��������������������ڴ��ļ����ֽ���
		ServletOutputStream sos= response.getOutputStream();
		//����ͼƬ����ͼƬ�ĳ��������ɫ���ɷ�ʽ��
		BufferedImage image=new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
		//����2Dͼ�Ļ���
		Graphics g=image.createGraphics();
		//1.����4λ�����֤��
		char[]code= generateCode();
		//2.������ĬĬ��ס--session�ﱣ������
		HttpSession session=request.getSession();
		session.setAttribute("code",new String(code));
		//3.����һ��ͼƬ
		//������
		drawBackground(g);
		//���ַ�
		drawCode(g, code);
		//4.���͸��ͻ���
		g.dispose(); //������ͼ
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", bos);
		byte[]buf= bos.toByteArray();
		sos.write(buf);
		bos.close();
		sos.close();
	}
	//�������ķ���
	public void drawBackground(Graphics g){
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 60, 20);
		//��100�����ŵ�
		for(int i=0;i<100;i++){
			int x= (int)Math.random()*60; //�������x����
			int y= (int)Math.random()*20; //���y����
			int red=(int)Math.random()*255; //�����ɫֵ
			int green=(int)Math.random()*255;	//�����ɫֵ
			int blue=(int)Math.random()*255;	//�����ɫֵ
			g.setColor(new Color(red, green, blue));
			g.drawOval(x, y, 1, 0);
		}	
	}
	//���ַ��ķ���
	public void drawCode(Graphics g,char[] code){
		g.setColor(Color.black);
		g.setFont(new Font(null, Font.ITALIC|Font.BOLD, 18)); //����
		g.drawString(code[0]+"", 1, 17);
		g.drawString(code[1]+"", 16, 18);
		g.drawString(code[2]+"", 31, 17);
		g.drawString(code[3]+"", 46, 16);
	}
	
	//����4λ�����֤��ķ���
	public char[] generateCode(){
		char[] code=new char[4];
		//������֤��ķ�Χ��0-9/a-z��36����
		String str="0123456789qwertyuiopasdfghjklzxcvbnm";
		for(int i=0;i<4;i++){
			//�±귶Χ0-35
			int index= (int)(Math.random()*36);
			code[i]=str.charAt(index);//����������ɵ��±��ȡ��Ӧ�ַ�
		}
		return code;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
