import java.io.*;
import java.util.*;

public class Program {

    public static TreeSet<String> dictionary = new TreeSet<>();
    public static List<String> wordListA = new ArrayList<>();
    public static List<String> wordListB = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Incorrect number of arguments.");
            System.err.println("Usage: java Program [inputA] [inputB]");
            System.exit(-1);
        }
        File fileA = new File(args[0]);
        File fileB = new File(args[1]);
        File fileOut = new File("dictionary.txt");

        try (FileReader fileReaderA = new FileReader(fileA);
             BufferedReader readerA = new BufferedReader(fileReaderA);
             FileReader fileReaderB = new FileReader(fileB);
             BufferedReader readerB = new BufferedReader(fileReaderB)
        ) {
            while (readerA.ready()) {
                String line = readerA.readLine();
                line = line.toLowerCase().replaceAll("[^a-zA-Z ]", "");
                String[] array = line.split("\\s+");
                wordListA.addAll(Arrays.asList(array));
            }
            while (readerB.ready()) {
                String line = readerB.readLine();
                line = line.toLowerCase().replaceAll("[^a-zA-Z ]", "");
                String[] array = line.split("\\s+");
                wordListB.addAll(Arrays.asList(array));
            }

            dictionary.addAll(wordListA);
            dictionary.addAll(wordListB);

        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        try (FileWriter fileWriter = new FileWriter(fileOut);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            for (String line : dictionary) {
                writer.write(line);
                writer.write("\n");
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        int[] vectorA = reflectFrequency(wordListA);
        int[] vectorB = reflectFrequency(wordListB);

        double numerator = numerator(vectorA, vectorB);
        double denominator = denominator(vectorA, vectorB);
        double similarity;
        if (numerator == 0 || denominator == 0) {
            similarity = 0.0;
        } else {
            similarity = numerator / denominator;
        }
        System.out.printf("Similarity = %.2f\n", similarity);

    }

    public static int[] reflectFrequency(List<String> wordList) {
        int[] vector = new int[dictionary.size()];
        List<String> dictList = new ArrayList<>(dictionary);
        ListIterator<String> it = dictList.listIterator();
        while (it.hasNext()) {
            String current = it.next();
            for (String word : wordList) {
                if (current.equals(word)) {
                    vector[it.nextIndex() - 1]++;
                }
            }
        }
        return vector;
    }


    public static int numerator(int[] A, int[] B) {
        int result = 0;

        for (int i = 0; i < A.length; i++) {
            result += A[i] * B[i];
        }
        return result;
    }

    public static double denominator(int[] A, int[] B) {
        double resultA = 0;
        double resultB = 0;

        for (int i = 0; i < A.length; i++) {
            resultA += A[i] * A[i];
            resultB += B[i] * B[i];
        }

        return Math.sqrt(resultA) * Math.sqrt(resultB);
    }
}
