import java.util.Scanner;

public class Program {
	
	private static final int MAX_SYMB_COUNT = 999; 
	public static void main(String[] args) {
		char[] textArray;
		int textSize = 0;
		short[] symbols = new short[65535];
		char[] topTenSymbs = new char[10];
		
		Scanner console = new Scanner(System.in);
		String text = console.nextLine();
		console.close();

		textArray = text.toCharArray();
		textSize = textArray.length;
		
		if (textArray.length == 0) {
			System.exit(0);
		}

		for (int i = 0; i < textSize; i++) {
			symbols[textArray[i]]++;
			if (symbols[textArray[i]] > MAX_SYMB_COUNT) {
				symbols[textArray[i]] = MAX_SYMB_COUNT;
			}
		}

		printHistoGramm(topTenSymbs, symbols);
	}

	private static void printHistoGramm(char[] topTenSymbs, short[] symbols) {
		for (int i = 0; i < 10; i++) {
			System.out.printf("%c", (char)topTenSymbs[i]);
		}
	}
}

