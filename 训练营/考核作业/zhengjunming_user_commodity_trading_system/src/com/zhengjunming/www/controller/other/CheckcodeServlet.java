package com.zhengjunming.www.controller.other;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成验证码图片
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/CheckcodeServlet")
public class CheckcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 大小
		int width = 90;
		int height = 30;

		// 创建图片
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics graphics = bufferedImage.getGraphics();
		// 颜色
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);

		// 边框
		graphics.setColor(Color.BLUE);
		graphics.drawRect(0, 0, width - 1, height - 1);

		// 内容
		Graphics2D graphics2d = (Graphics2D) bufferedImage.getGraphics();

		// 字体
		graphics2d.setFont(new Font("宋体", Font.BOLD, 18));

		String content = "1234567890";
		Random random = new Random();
		int x = 10;
		int y = 20;

		StringBuffer stringBuffer = new StringBuffer();

		for (int i = 0; i < 4; i++) {
			graphics2d.setColor(getRandomColor());
			// 旋转角度
			double angle = random.nextInt(60) - 30;
			// 将旋转角度 换算弧度
			double theta = angle / 180 * Math.PI;

			int index = random.nextInt(content.length());
			char letter = content.charAt(index);

			stringBuffer.append(letter);

			graphics2d.rotate(theta, x, y);
			graphics2d.drawString(letter + "", x, y);
			// 将角度还原
			graphics2d.rotate(-theta, x, y);
			x += 20;
		}

		String checkcode = stringBuffer.toString();

		// 将验证码存入session
		request.getSession().setAttribute("checkcode_session", checkcode);

		// 绘制随机干扰线
		int x1;
		int x2;
		int y1;
		int y2;
		graphics.setColor(Color.LIGHT_GRAY);

		for (int i = 0; i < 10; i++) {
			x1 = getRandomNumber(width);
			x2 = getRandomNumber(width);
			y1 = getRandomNumber(height);
			y2 = getRandomNumber(height);
			graphics.setColor(getRandomColor());
			graphics.drawLine(x1, y1, x2, y2);
		}

		// 释放内存
		graphics.dispose();

		// 输出图片
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
	}

	/**
	 * 获取随机数
	 * 
	 * @param number
	 * @return
	 */
	private int getRandomNumber(int number) {
		Random random = new Random();
		return random.nextInt(number);
	}

	/**
	 * 获取随机颜色
	 * 
	 * @return
	 */
	private Color getRandomColor() {
		int r = getRandomNumber(255);
		int g = getRandomNumber(255);
		int b = getRandomNumber(255);
		return new Color(r, g, b);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}
