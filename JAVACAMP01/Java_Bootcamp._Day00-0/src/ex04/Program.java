import java.util.Scanner;

public class Program {

    private static final int MAX_SYMB_COUNT = 999;
    private static final int MAX_CHAR_CODE_VALUE = 65535;
    static short[] symbolsOccurrences = new short[MAX_CHAR_CODE_VALUE + 1];
    static short[] hashes = new short[10];
    static int len = 0;

    public static void main(String[] args) {
        char[] textArray;

        Scanner console = new Scanner(System.in);
        String text = console.nextLine();
        console.close();

        textArray = text.toCharArray();
        int textSize = text.length();
        if (textSize == 0) {
            System.exit(0);
        }
        int resultSize = textParsing(textArray, textSize);
        short[] symbols = new short[resultSize];
        for (int i = 0, j = 0; i < MAX_CHAR_CODE_VALUE + 1; i++) {
            if (symbolsOccurrences[i] > 0) {
                symbols[j] = (short)i;
                j++;
            }
        }
        short[] sorted = new short[10];
        for (int i = 0; i < 10; i++) {
            findMaxAndAdd(symbols, resultSize, sorted);
        }
        countHashes(sorted);
        printHistoGramm(sorted);
    }


    private static int textParsing(char[] textArray, int textSize) {
        int resultSize = 0;

        for (int i = 0; i < textSize; i++) {
            if (symbolsOccurrences[textArray[i]] == 0) {
                resultSize++;
            }
            symbolsOccurrences[textArray[i]]++;
            if (symbolsOccurrences[textArray[i]] > MAX_SYMB_COUNT) {
                symbolsOccurrences[textArray[i]] = MAX_SYMB_COUNT;
            }
        }
        return resultSize;
    }

    private static void countHashes(short[] sorted) {
        short maxOccurr = findMaxOccurr(sorted);

        if (maxOccurr > 10) {
            for (int i = 0; i < 10; i++) {
                hashes[i] = (short) ((symbolsOccurrences[sorted[i]] * 100 / maxOccurr) / 10);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                hashes[i] = symbolsOccurrences[sorted[i]];
            }
        }
    }

    private static short findMaxOccurr(short[] sorted) {
        short max = 0;
        for (int i = 0; i < 10; i++) {
            if (symbolsOccurrences[sorted[i]] > max) {
                max = symbolsOccurrences[sorted[i]];
            }
        }
        return max;
    }
    private static void findMaxAndAdd( short[] symbols, int resultSize, short[] sorted) {
        int maxCountValue = 0;
        short maxSymb = 0;
        for (int i = 0; i < resultSize; i++) {
            if (symbolsOccurrences[symbols[i]] > maxCountValue) {
                maxCountValue = symbolsOccurrences[symbols[i]];
                maxSymb = symbols[i];
            }
        }
        sorted[len] = maxSymb;
        len++;
        for (int i = 0; i < resultSize; i++) {
            if (maxSymb == symbols[i]) {
                symbols[i] = 0;
                break;
            }
        }
    }

    private static void printHistoGramm(short[] sorted) {
        System.out.println();
        short[][] matrix = new short[10][12];
        for (int y = 0; y < 10; y++) {

            for (int x = 0; x < 12; x++) {
                if (x == 0) {
                    matrix[y][x] = sorted[y];
                }
                else {
                    if (hashes[y] > 0) {
                        matrix[y][x] = -3;
                        hashes[y]--;
                    } else {
                        matrix[y][x] = symbolsOccurrences[sorted[y]];
                        break;
                    }
                }

            }

        }
        for (int y = 11; y >= 0; y--) {
            for (int x = 0; x < 10; x++) {
                if (y != 0 && matrix[x][y] != -3) {
                    if (matrix[x][y] == 0) {
                        System.out.printf("%4c", ' ');
                    } else {
                        System.out.printf("%4d", matrix[x][y]);
                    }
                }
                else {
                    if (matrix[x][y] == -3) {
                        System.out.printf("%4c", '#');
                    } else {
                        System.out.printf("%4c", matrix[x][y]);
                    }
                }
            }
            System.out.println();
        }
    }
}
