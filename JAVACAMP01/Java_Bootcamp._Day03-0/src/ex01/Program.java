public class Program {
	
	public static final String ERROR_ARGS = "Error: Incorrect input";
	public static final String USAGE = "Usage: java Program --count=50";
	public static final String ERROR_COUNT = "Error: Only numbers greater than 0";
	public static final String ERROR_NO_COUNT = "Error: The number after \"count=\" is not specified";

	public enum Queue {
		PRODUCER,
		CONSUMER 
	}

	public static Queue queue = Queue.CONSUMER;

	public static synchronized void produceEgg() {
		
		if (queue == Queue.PRODUCER) {
			try {
				Program.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Egg");
		queue = Queue.PRODUCER;
		Program.class.notify();
	}

	public static synchronized void consumeHen() {
		
		if (queue == Queue.CONSUMER) {
			try {
				Program.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Hen");
		queue = Queue.CONSUMER;
		Program.class.notify();
	}

	public static void main(String[] args) throws Exception {
		
		int count = 0;

		if (args.length != 1 || !args[0].startsWith("--count=")) {
			System.err.println(ERROR_ARGS);
			System.err.println(USAGE);
			System.exit(-1);
		} else {
			try {
				count = Integer.parseInt(args[0].substring(8));
			} catch (Exception e ) {
				System.err.println(ERROR_NO_COUNT);
				System.err.println(USAGE);
				System.exit(-1);
			}
			if (count <= 0) {
				System.err.println(ERROR_COUNT);
				System.err.println(USAGE);
				System.exit(-1);
			}
		}

		MyThreads egg = new MyThreads("Egg", count);
		MyThreads hen = new MyThreads("Hen", count);

		egg.start();
		hen.start();
	}

}