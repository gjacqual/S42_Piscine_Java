import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int count = 0;

		while (console.hasNextInt()) {
			int number = console.nextInt();
			if (number < 2) {
				System.err.println("Illegal Argument");
				System.exit(-1);
			}
			if (number == 42) {
				break;
			}
			if (isPrimeNumber(sumDigits(number))) {
				count++;
			}
		}
		
		console.close();
		System.out.println("Count of coffee-request - " + count);
	}

	private static boolean isPrimeNumber(int number) {
		if (number < 2) {
			return false;
		}
		if (number == 2 || number == 3) {
			return true;
		} else {
			int i = 2;
			while (i * i <= number) {
				if (number % i == 0) {
					return false;
				}
				i++;
			}
		}
		return true;
	}

	private static int sumDigits(int number) {
        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        return sum;
	}
}