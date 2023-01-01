package edu.school21.printer.logic;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import java.net.URL;

import java.awt.Color;
import java.awt.image.BufferedImage;

@Parameters(separators = "=")
public class ImgCharLogic {
	
	@Parameter(names = {"--white"}, arity = 1)
	private String whiteVal;
	@Parameter(names = {"--black"}, arity = 1)
	private String blackVal;

	private URL imgFile;
	
	public ImgCharLogic(URL imgPath) {

		this.imgFile = imgPath;
	}

	public void printImgConsole() throws IOException {
		
		Ansi.BColor.valueOf(whiteVal);
    	Ansi.BColor.valueOf(blackVal);

		BufferedImage imgBuf = ImageIO.read(imgFile);
		ColoredPrinter colorPrint = new ColoredPrinter.Builder(1, false).foreground(Ansi.FColor.NONE).background(Ansi.BColor.NONE).build();
		
		for (int x = 0; x < imgBuf.getWidth(); x++) {
			for (int y = 0; y < imgBuf.getHeight(); y++) {
				int color = imgBuf.getRGB(y,x);
				if (color == Color.BLACK.getRGB()) {
					colorPrint.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(blackVal));
				} else if (color == Color.WHITE.getRGB()) {
					colorPrint.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(whiteVal));
				}
			}
			System.out.println();
		}
	}
	
}
