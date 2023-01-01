package ex00;

public class Program {
	public static void main(String[] args) {
		User user01 = new User(101, "Maximus", 100);
		User user02 = new User(102, "Ivan", 500);
		User user03 = new User(103, "Elena", 0);
		User user04 = new User(104, "Neo", 10_000_000);

		System.out.println(user01);
		System.out.println(user02);
		System.out.println(user03);
		System.out.println(user04);

		Transaction transaction01 = new Transaction(user01, user02, 90, Transaction.Category.DEBITS);
		Transaction transaction02 = new Transaction(user02, user01, -90, Transaction.Category.CREDITS);
		System.out.println(transaction01);
		System.out.println(transaction02);

		User user05 = new User(105, "Dude", -1);
		System.out.println(user05);

	}
}
