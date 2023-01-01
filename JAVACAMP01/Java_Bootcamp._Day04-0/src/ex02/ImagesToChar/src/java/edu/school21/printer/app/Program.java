package edu.school21.printer.app;

import java.io.IOException;

import java.net.URL;

import com.beust.jcommander.JCommander;

import edu.school21.printer.logic.ImgCharLogic;

public class Program {
	public static void main(String[] args) throws Exception, IOException {
		if (args.length != 2)
			throw new Exception("Incorrect number of arguments entered");
		
		char white;
		char black;
		URL filepath = Program.class.getResource("/resources/image.bmp");

		ImgCharLogic img = new ImgCharLogic(filepath);
		JCommander.newBuilder().addObject(img).build().parse(args);
		try {
			img.printImgConsole();
		} catch (IOException e) {
			System.err.println("System Error: The conversion failed");
		}

	}
}