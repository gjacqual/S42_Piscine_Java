import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int numWeek = 1;
		long savedGrades = 0;
		long revSavedGrades = 0;

		while (console.hasNext()) {
			String strWeek = console.nextLine();

			if (strWeek.equals("42") || numWeek > 18) {
				break;
			}
			if (!strWeek.equals("Week" + " " + numWeek)) {
				System.err.println("Illegal Argument");
				System.exit(-1);
			}

			savedGrades = saveMinGrages(console, savedGrades);
			numWeek++;
		}
		console.close();

		while (savedGrades > 0) {
			revSavedGrades *= 10;
			revSavedGrades += savedGrades % 10;
			savedGrades /= 10;
		}
		printGraph(revSavedGrades);
	}

	private static int getMinWeekGrade(Scanner console) {
		int min = 9;

		for (int i = 1; i <= 5; i++) {
			int cur = console.nextInt();
			if (cur < 1 || cur > 9) {
				System.err.println("Illegal Argument");
				System.exit(-1);
			}
			if (cur < min) {
				min = cur;
			}
		}
		console.nextLine();
		return min;
	}

	private static long saveMinGrages(Scanner console, long savedGrades) {
		int minWeekGrade = getMinWeekGrade(console);

		savedGrades *= 10;
		savedGrades += minWeekGrade;

		return savedGrades;
	}

	private static void printGraph(long savedGrades) {
		for (int i = 1; i <= 18; i++) {
			int gradesRemains = (int) savedGrades % 10;
			if (gradesRemains == 0) {
				break;
			}
			System.out.print("Week ");
			System.out.print(i);
			System.out.print(" ");

			printСolumn(gradesRemains);
			savedGrades /= 10;
		}

	}

	private static void printСolumn(long gradesRemains) {
		for (int i = 1; i <= gradesRemains; i++) {
			System.out.print("=");
		}
		System.out.println(">");
	}
}
