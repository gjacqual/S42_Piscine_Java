public class MyThreads extends Thread {
	private int count = 0;
	private String answer = null;

	public MyThreads(String answer, int count) {
		this.answer = answer;
		this.count = count;
	}

	@Override
	public void run() {
		for (int i = 0; i < count; i++) {
			if (answer.equals("Egg")) {
				Program.produceEgg();
			} else if (answer.equals("Hen")) {
				Program.consumeHen();
			} else {
				System.err.println("Incorrect argument");
				System.exit(-1);
			}
			
		}
	}
}
