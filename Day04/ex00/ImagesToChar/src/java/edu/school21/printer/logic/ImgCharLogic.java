package edu.school21.printer.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImgCharLogic {
	private char whiteVal;
	
	private char blackVal;

	private File imgFile;
	
	public ImgCharLogic(char white, char black, String imgPath) {
		this.whiteVal = white;
		this.blackVal = black;
		this.imgFile = new File(imgPath);
	}

	public void printImgConsole() throws IOException {
		BufferedImage imgBuf = ImageIO.read(new FileInputStream(imgFile.getAbsolutePath()));
		for (int x = 0; x < imgBuf.getWidth(); x++) {
			for (int y = 0; y < imgBuf.getHeight(); y++) {
				int color = imgBuf.getRGB(y,x);
				if (color == Color.BLACK.getRGB()) {
					System.out.print(blackVal);
				} else if (color == Color.WHITE.getRGB()) {
					System.out.print(whiteVal);
				}
			}
			System.out.println();
		}
	}
	
}
