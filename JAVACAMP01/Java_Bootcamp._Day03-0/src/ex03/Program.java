import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Program {
    public static final String ERROR_ARGS = "Error: Incorrect input";
    public static final String USAGE = "Usage: java Program --threadsCount=3";
    public static final String ERROR_COUNT = "Error: Only numbers greater than 0";
    public static final String ERROR_NO_COUNT = "Error: The number is not specified";
    public static final String ERROR_COUNT_THREADS = "Error: Incorrect Threads count";

    public static final String FILE_URLS = "files_urls.txt";

    public static final Object monitor = new Object();


    public static void main(String[] args) throws FileNotFoundException {

        int threadsCount = 0;

        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            System.err.println(ERROR_ARGS);
            System.err.println(USAGE);
            System.exit(-1);
        } else {
            try {
                threadsCount = Integer.parseInt(args[0].substring(15));
            } catch (Exception e) {
                System.err.println(ERROR_NO_COUNT);
                System.err.println(USAGE);
                System.exit(-1);
            }
            if (threadsCount < 1) {
                System.err.println(ERROR_COUNT);
                System.err.println(USAGE);
                System.exit(-1);
            }
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_URLS)));

        Path downloads = Paths.get("downloads");


        if (!Files.exists(downloads)) {
            try {
                Files.createDirectory(downloads);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<Thread> myThreads = new ArrayList<>(threadsCount);


        for (int i = 0; i < threadsCount; i++) {
            myThreads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String urlAndNumber = "";
                            synchronized (monitor) {
                                if (bufferedReader.ready()) {
                                    urlAndNumber = bufferedReader.readLine();
                                }
                                if (urlAndNumber.equals("")) {
                                    break;
                                }
                                String[] urlArray = urlAndNumber.split(" ");
                                Path filename = Paths.get(urlArray[1]).getFileName();


                                System.out.println(Thread.currentThread().getName() + " start download file number " + Integer.parseInt(urlArray[0]));

                                URL url = new URL(urlArray[1]);
                                InputStream inputStream = url.openStream();
                                Files.copy(inputStream, new File("downloads/" + filename).toPath());
                                inputStream.close();

                                System.out.println(Thread.currentThread().getName() + " finish download file number " + Integer.parseInt(urlArray[0]));

                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }));
        }
        for (Thread thread : myThreads)
            thread.start();
    }
}
