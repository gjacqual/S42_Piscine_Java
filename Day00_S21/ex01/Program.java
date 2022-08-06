import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int steps = 0;
		boolean isPrime = true;

		if (!console.hasNextInt()) {
			System.err.println("Illegal Argument");
			System.exit(-1);
		}

		int number = console.nextInt();

		console.close();

		if (number < 2) {
			System.err.println("Illegal Argument");
			System.exit(-1);
		}

		if (number == 2 || number == 3) {
			steps = 1;
			System.out.println(isPrime + " " + steps);
		} else {
			int i = 2;
			while (i * i <= number) {
				steps++;
				if (number % i == 0) {
					isPrime = false;
					break;
				}
				i++;
			}

			System.out.println(isPrime + " " + steps);
		}
	}
}