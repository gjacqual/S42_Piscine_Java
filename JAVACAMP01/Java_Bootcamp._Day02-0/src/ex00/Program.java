import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Map<String, String> signatures = parsSignatures();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("42")) {
                break;
            }

            try (FileInputStream inputCheckFile = new FileInputStream(input);
                 OutputStream outputStream = new FileOutputStream("./result.txt", true)) {
                StringBuilder tempLineBuilder = new StringBuilder();

                for (int i = 0; inputCheckFile.available() > 0 && i < 10; i++) {
                    tempLineBuilder.append(String.format("%02X", inputCheckFile.read()));
                }
                String checkFileSignature = tempLineBuilder.toString();
                System.out.println(checkFileSignature);
                definition(checkFileSignature, signatures, outputStream);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
        scanner.close();
    }

    private static Map<String, String> parsSignatures() {
        Map<String, String> signatures = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream inputStream = new FileInputStream("./signatures.txt")) {
            int i;
            while ((i = inputStream.read()) != -1) {
                if (inputStream.available() < 0 || (char) i == '\n') {
                    String[] line = stringBuilder.toString().split(", ");
                    signatures.put(line[0], line[1].trim().replaceAll(" ", ""));
                    stringBuilder.setLength(0);
                    continue;
                }
                stringBuilder.append((char) i);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return signatures;
    }

    private static void definition(String checkFileSignature,
                                   Map<String, String> signatures,
                                   OutputStream outputStream) throws IOException {
        boolean identified = false;
        for (String key : signatures.keySet()) {
            identified = checkFileSignature.contains(signatures.get(key));
            if (identified) {
                System.out.println("PROCESSED");
                outputStream.write(key.getBytes());
                outputStream.write("\n".getBytes());
                break;
            }
        }
        if (!identified) {
            System.out.println("UNDEFINED");
        }
    }

}
