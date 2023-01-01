package edu.school21.printer.app;

import java.io.IOException;

import edu.school21.printer.logic.ImgCharLogic;

public class Program {
	public static void main(String[] args) throws Exception, IOException {
		if (args.length != 3)
			throw new Exception("Incorrect number of arguments entered");
		
		char white = args[0].charAt(0);
		char black = args[1].charAt(0); 
		String filepath = args[2];

		ImgCharLogic img = new ImgCharLogic(white, black, filepath);
		try {
			img.printImgConsole();
		} catch (IOException e) {
			System.err.println("System Error: The conversion failed");
		}

	}
}