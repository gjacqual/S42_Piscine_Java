package ex00;

public class Program {
	public static void main(String[] args) {
		User user1 = new User(101, "Maximus", 100);
		User user2 = new User(102, "Ivan", 500);
		User user3 = new User(103, "Elena", -10);

		System.out.println("id " + user1.getIdentifier() + ", " + user1.getName() + ", $" + user1.getBalance());
		System.out.println("id " + user2.getIdentifier() + ", " + user2.getName() + ", $" + user2.getBalance());
		System.out.println("id " + user3.getIdentifier() + ", " + user3.getName() + ", $" + user3.getBalance());

		Transaction transaction01 = new Transaction(user1, user2, 90);
		System.out.println(transaction01.getIdentifier() + " | from " + transaction01.getSender().getName() + " to " + transaction01.getRecipient().getName());
		System.out.println(transaction01.getAmount() + " | " + transaction01.getTransCategory());

		System.out.println("id " + user1.getIdentifier() + ", " + user1.getName() + ", $" + user1.getBalance());
		System.out.println("id " + user2.getIdentifier() + ", " + user2.getName() + ", $" + user2.getBalance());

		Transaction transaction02 = new Transaction(user2, user3, 450);
		System.out.println(transaction02.getIdentifier() + " | from " + transaction02.getSender().getName() + " to " + transaction02.getRecipient().getName());
		System.out.println(transaction02.getAmount() + " | " + transaction02.getTransCategory());

		System.out.println("id " + user2.getIdentifier() + ", " + user2.getName() + ", $" + user2.getBalance());
		System.out.println("id " + user3.getIdentifier() + ", " + user3.getName() + ", $" + user3.getBalance());

	}
}