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
		//由于发的是图片,更改响应你内容的类型
		response.setContentType("image/jpeg");
		//由于发的是图片，更改输出流———用于传文件的字节流
		ServletOutputStream sos= response.getOutputStream();
		//创建图片对象（图片的长，宽和颜色构成方式）
		BufferedImage image=new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
		//创建2D图的画笔
		Graphics g=image.createGraphics();
		//1.生成4位随机验证码
		char[]code= generateCode();
		//2.服务器默默记住--session里保存起来
		HttpSession session=request.getSession();
		session.setAttribute("code",new String(code));
		//3.生成一张图片
		//画背景
		drawBackground(g);
		//画字符
		drawCode(g, code);
		//4.发送给客户端
		g.dispose(); //结束画图
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", bos);
		byte[]buf= bos.toByteArray();
		sos.write(buf);
		bos.close();
		sos.close();
	}
	//画背景的方法
	public void drawBackground(Graphics g){
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 60, 20);
		//画100个干扰点
		for(int i=0;i<100;i++){
			int x= (int)Math.random()*60; //生成随机x坐标
			int y= (int)Math.random()*20; //随机y坐标
			int red=(int)Math.random()*255; //随机红色值
			int green=(int)Math.random()*255;	//随机绿色值
			int blue=(int)Math.random()*255;	//随机蓝色值
			g.setColor(new Color(red, green, blue));
			g.drawOval(x, y, 1, 0);
		}	
	}
	//画字符的方法
	public void drawCode(Graphics g,char[] code){
		g.setColor(Color.black);
		g.setFont(new Font(null, Font.ITALIC|Font.BOLD, 18)); //字体
		g.drawString(code[0]+"", 1, 17);
		g.drawString(code[1]+"", 16, 18);
		g.drawString(code[2]+"", 31, 17);
		g.drawString(code[3]+"", 46, 16);
	}
	
	//生成4位随机验证码的方法
	public char[] generateCode(){
		char[] code=new char[4];
		//定义验证码的范围（0-9/a-z共36个）
		String str="0123456789qwertyuiopasdfghjklzxcvbnm";
		for(int i=0;i<4;i++){
			//下标范围0-35
			int index= (int)(Math.random()*36);
			code[i]=str.charAt(index);//根据随机生成的下标获取对应字符
		}
		return code;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
