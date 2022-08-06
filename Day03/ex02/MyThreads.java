import java.util.List;

public class MyThreads extends Thread {
	private int sumThread = 0;
	private static int sumAll = 0;
	private final int beginIndex;
	private final int endIndex;

	public MyThreads(List<Integer> threadsList, int beginIndex, int endIndex) {
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
		for (int val : threadsList) {
			sumThread += val;
		}
	}

	public static int getSumAll() {
		return sumAll;
	}

	private static synchronized void printThreadInfo(int sumThread, int beginIndex, int endIndex) {
		System.out.print(Thread.currentThread().getName());
		System.out.print(": from " + beginIndex);
		System.out.print(" to " + (endIndex - 1));
		System.out.println("sum is " + sumThread);
		sumAll += sumThread;
	}

	@Override
	public void run() {
		printThreadInfo(sumThread, beginIndex, endIndex);
	}
}
