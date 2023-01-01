import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Program {

    static private Path currentDir;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Incorrect number of arguments.");
            System.err.println("Usage: java Program --current-folder=[DIRNAME]");
            System.exit(-1);
        }
        if (!args[0].substring(0, 16).equals("--current-folder")) {
            System.err.println("Incorrect input");
            System.err.println("Usage: java Program --current-folder=[DIRNAME]");
            System.exit(-1);
        }
        Path rootDir = Paths.get(args[0].substring(17));
        if (!Files.isDirectory(rootDir)) {
            System.err.println("Incorrect path " + rootDir);
            System.exit(-1);
        }
        if (Files.exists(rootDir)) {
            System.out.println(rootDir);
        } else {
            System.err.println("Incorrect path " + rootDir);
            System.exit(-1);
        }
        currentDir = rootDir;

        try (Scanner scanner = new Scanner(System.in)) {

            while (scanner.hasNextLine()) {
                String command = scanner.nextLine().trim();
                String[] cmdArgs = command.split(" ");

                if (cmdArgs[0].equals("ls")) {
                    cmdLs();
                } else if (cmdArgs[0].equals("cd")) {
                    if (cmdArgs.length != 2) {
                        System.err.println(cmdArgs[0] + ": too many/few arguments");
                    } else {
                        cmdCd(cmdArgs[1]);
                    }
                } else if (cmdArgs[0].equals("mv")) {
                    if (cmdArgs.length != 3) {
                        System.err.println(cmdArgs[0] + ": too many/few arguments");
                    } else {
                        cmdMv(cmdArgs[1], cmdArgs[2]);
                    }
                } else if (cmdArgs[0].equals("exit")) {
                    System.exit(0);
                } else if (!cmdArgs[0].equals("")) {
                    System.out.println("Command " + cmdArgs[0] + " not found");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

    private static void cmdLs() {
        int length = currentDir.toString().length();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(currentDir)) {
            for (Path filename : directoryStream) {
                System.out.println(filename.toString().substring(length + 1) + " " + Files.size(filename) / 1024 + " KB");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void cmdCd(String toDir) {
        Path distDir = currentDir.resolve(Paths.get(toDir));
        if (Files.exists(distDir) && Files.isDirectory(distDir)) {
            currentDir = distDir.normalize();
            System.out.println(currentDir);
        } else {
            System.err.println("cd: " + toDir + ": No such file or directory");
        }

    }

    private static void cmdMv(String filename, String toDir) throws IOException {
        Path distDir = currentDir.resolve(Paths.get(toDir));
        Path movable = currentDir.resolve(Paths.get(filename));

        if (Files.isRegularFile(movable)) {
            if (Files.isDirectory(distDir)) {
                distDir = Paths.get(distDir + "/" + movable.getFileName());
            }
            Files.move(movable, distDir, REPLACE_EXISTING);
        } else {
            System.err.println("mv: cannot stat " + filename + ": No such file or directory");
        }
    }
}
