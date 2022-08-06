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
			System.out.println(this.answer);
		}
	}
}
