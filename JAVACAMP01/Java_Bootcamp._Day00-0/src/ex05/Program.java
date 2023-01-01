import java.util.Scanner;

public class Program {

    public static final int MAX_NUMBER_STUDENTS = 10;
    public static final int MAX_MONTH_DAY = 31;
    public static final int DAYS = 7;
    public static final String[] ABBREV_DAYS = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
    public static final int HOURS = 6;
    private static String[] students;
    private static int studentsCount = 0;
    private static int[][] timetable;
    private static int[][][] journal;
    private static int lessonsCount = 0;

    public static void main(String[] args) {

        students = new String[MAX_NUMBER_STUDENTS];
        timetable = new int[DAYS][HOURS];
        journal = new int[studentsCount][MAX_MONTH_DAY][HOURS];

        Scanner console = new Scanner(System.in);

        createStudentArray(console);
        AddShedule(console);
        RecordAttendance(console);
        displayTimetable();

        console.close();
    }

    private static void createStudentArray(Scanner console) {
        while (console.hasNextLine()) {
            String line = console.nextLine();
            if (line.equals(".")) {
                break;
            }
            if (studentsCount >= MAX_NUMBER_STUDENTS) {
                System.err.println("Error: The list is already full");
                System.exit(-1);
            }
            if (isIllegalArg(line)) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
            students[studentsCount] = line;
            ++studentsCount;
        }

        if (studentsCount == 0) {
            System.err.println("Error: The student list is empty");
            System.exit(-1);
        }
    }

    private static void AddShedule(Scanner console) {
        while (console.hasNextLine()) {
            String line = console.nextLine();
            if (line.equals(".")) {
                break;
            }
            if (lessonsCount >= 10) {
                System.err.println("Error: total classes per week cannot exceed 10");
                System.exit(-1);
            }

            char[] lineArray = line.toCharArray();
            if (lineArray[1] != ' ' || line.length() > 4) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
            if (lineArray[0] < '1' || lineArray[0] > '6') {
                System.err.println("Error: The hour is specified incorrectly");
                System.exit(-1);
            }
            String abbr = "" + lineArray[2] + lineArray[3];
            int dayOfLesson = -1;
            for (int i = 0; i < 7; i++) {
                if (abbr.equals(ABBREV_DAYS[i])) {
                    dayOfLesson = i;
                }
            }
            if (dayOfLesson == -1) {
                System.err.println("Illegal Argument - Day of Week");
                System.exit(-1);
            }
            int hour = (lineArray[0] - '1');
            if (timetable[dayOfLesson][hour] != 0) {
                System.err.println("Error: The lesson is already scheduled for this hour");
                System.exit(-1);
            } else {
                timetable[dayOfLesson][hour] += 1;
                ++lessonsCount;
            }

        }
        if (lessonsCount == 0) {
            System.err.println("Error: The schedule is empty");
            System.exit(-1);
        }
    }

    private static void RecordAttendance(Scanner console) {


        while (console.hasNextLine()) {
           String line = console.nextLine();
           if (line.equals(".")) {
               break;
           }
            if (line.length() < 1) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
           String studentName  = "";
            char[] studentNameArray = line.toCharArray();
            for  (int i = 0; studentNameArray[i] != ' '; i++) {
               studentName += studentNameArray[i];
            }
            if (isIllegalArgInCalendar(studentName)) {
                System.err.println("Error: Incorrect Student Name");
                System.exit(-1);
            }

            int index = studentName.length();
            if (studentNameArray[index] != ' ') {
                System.err.println("Illegal Argument");
                System.exit(-1);
            } else {
                index++;
            }
            int time = studentNameArray[index] - '1';
            int dayofMonth = 0;
            index++;
            if (studentNameArray[index] != ' ') {
                System.err.println("Illegal Argument");
                System.exit(-1);
            } else {
                index++;
            }
            while (studentNameArray[index] != ' ') {
                dayofMonth = dayofMonth * 10 + (studentNameArray[index] - '0');
                index++;
            }
            if (dayofMonth > MAX_MONTH_DAY - 1) {
                System.err.println("Illegal Argument - day of Month");
                System.exit(-1);
            }
            index++;
            String status = "";
            while (index < line.length()) {
                status += studentNameArray[index];
                index++;
            }

            int stuIndex = findStudentIndex(studentName);
            if (journal[stuIndex][dayofMonth][time] == 0) {
                if (status.equals("HERE"))
                    journal[stuIndex][dayofMonth][time] = 1;
                else
                    journal[stuIndex][dayofMonth][time] = -1;
            }

        }

    }

    private static void displayTimetable() {
        int i = 0;
        System.out.printf("%10s", "");
        for (i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 6; k++) {
                    if (i == 0 && j == 0 || 7 * i + j > 30)
                        continue;
                    if (timetable[j][k] != 0) {
                        System.out.printf("%d:00 %s %2d|", k + 1, ABBREV_DAYS[j], 7 * i + j);
                    }
                }
            }
        }
        System.out.println();

        for (i = 0; i < studentsCount; i++) {
            System.out.printf("%10s", students[i]);
            for (int j = 1; j < 31; j++) {
                for (int k = 0; k < 6; k++) {
                    if (timetable[j % 7][k] != 0) {
                        if (journal[i][j][k] != 0)
                            System.out.printf("%10d|", journal[i][j][k]);
                        else
                            System.out.printf("%10s|", "");
                    }
                }
            }
            System.out.println();
        }
    }

    private static boolean isIllegalArg(String line) {
        if (line.length() >= 10 || line.length() < 1)   {
            return true;
        }
        for (int i = 0; i < studentsCount; i++) {
            if (line.equals(students[i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean isIllegalArgInCalendar(String line) {
        for (int i = 0; i < studentsCount; i++) {
            if (line.equals(students[i])) {
                return false;
            }
        }
        return true;
    }

    public static int findStudentIndex(String current) {
        for (int i = 0; i < studentsCount; i++) {
            if (current.equals(students[i]))
                return i;
        }
        return -1;
    }

}
