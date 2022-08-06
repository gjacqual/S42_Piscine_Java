import java.util.Arrays;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Program {
	
	public static final String ERROR_ARGS = "Error: Incorrect input";
	public static final String USAGE = "Usage: java Program --arraySize=13 --threadsCount=3";
	public static final String ERROR_COUNT = "Error: Only numbers greater than 0";
	public static final String ERROR_NO_COUNT = "Error: The number is not specified";
	public static final String ERROR_ARR_SIZE = "Error: Incorrect Array Size";
	public static final String ERROR_COUNT_THREADS = "Error: Incorrect Threads count";
	public static final int MAX_ARR_ELEMENTS = 2000000;
	public static final int MAX_VAL_MODULO = 1000;

	public static void main(String[] args) throws Exception {
		
		int arraySize = 0;
		int threadsCount = 0;
		int simpleSum = 0;
		int partLen;
		boolean isOdd = false;

		if (args.length != 2 || !args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")) {
			System.err.println(ERROR_ARGS);
			System.err.println(USAGE);
			System.exit(-1);
		} else {
			try {
				arraySize = Integer.parseInt(args[0].substring(12));
				threadsCount = Integer.parseInt(args[1].substring(15));
			} catch (Exception e ) {
				System.err.println(ERROR_NO_COUNT);
				System.err.println(USAGE);
				System.exit(-1);
			}
			if (arraySize <= 0 || arraySize > MAX_ARR_ELEMENTS) {
				throw new Exception(ERROR_ARR_SIZE);
			}
			if (threadsCount > arraySize) {
				throw new Exception(ERROR_COUNT_THREADS);
			}
		}
		
		List<Integer> randArray = new ArrayList<>(arraySize);
		Random randInt = new Random();
		for (int i = 0; i < arraySize; i++) {
			int tempInt = randInt.nextInt(MAX_VAL_MODULO);
			randArray.add(tempInt);
			simpleSum += tempInt;
		}
		System.out.println("Sum: " + simpleSum);
		
		
		partLen = arraySize / threadsCount + 1;
		int equalParts = partLen * threadsCount;
		isOdd = (equalParts != arraySize);
		int lastPart = 0;
		if (isOdd) {
			lastPart = arraySize - equalParts; 
		}

		List<Thread> myThreads = new ArrayList<>(threadsCount);

		int beginIndex = 0;
		int endIndex = 0;
		for (int i = 0 ; i < threadsCount; i++) {
			beginIndex = i * partLen;
			endIndex = (i + 1) * partLen;

			if (endIndex > arraySize) {
				endIndex = arraySize;
			}

			List<Integer> partArray = randArray.subList(beginIndex, endIndex);
			Thread thread = new MyThreads(partArray, beginIndex, endIndex);
			thread.setName("Thread " + (1 + i));
			myThreads.add(thread);
		}
		for (Thread thread : myThreads)
            thread.start();
        for (Thread thread : myThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
            
				e.printStackTrace();
            }
        }
		System.out.println("Sum by threads: " + MyThreads.getSumAll());
	}

}