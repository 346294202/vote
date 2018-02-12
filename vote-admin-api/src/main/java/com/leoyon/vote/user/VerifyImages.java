package com.leoyon.vote.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerifyImages {

	public static void build(String strEnsure, int width, int height, OutputStream os) throws IOException {

		if (width <= 0) {
			width = 60;
		}
		if (height <= 0) {
			height = 20;
		}

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// Gets the graphics context
		Graphics g = image.getGraphics();
		// Set background color
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);
		// Draw border
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);
		// Random verification code
		// Set the validation code is displayed in the image , If you want to
		// generate more bits of code , Increase the drawString statement
		g.setColor(Color.black);
		g.setFont(new Font("Atlantic Inline", Font.PLAIN, 18));
		String str = strEnsure.substring(0, 1);
		g.drawString(str, 8, 17);
		str = strEnsure.substring(1, 2);
		g.drawString(str, 20, 15);
		str = strEnsure.substring(2, 3);
		g.drawString(str, 35, 18);
		str = strEnsure.substring(3, 4);
		g.drawString(str, 45, 15);
		// Randomly generated 10 interference point

		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			g.drawOval(x, y, 1, 1);
		}
		// Free graphics context
		g.dispose();
		
		ImageIO.write(image, "JPEG", os);
	}
}
