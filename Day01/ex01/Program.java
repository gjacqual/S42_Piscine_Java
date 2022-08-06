package ex01;

public class Program {
	public static void main(String[] args) {
		User user1 = new User("Maximus", 100);
		User user2 = new User("Ivan", 500);
		User user3 = new User("Elena", -100);
		User user4 = new User("Jack", -100);
		User user5 = new User("Kate", -100);
		User user6 = new User("Macos", -100);

		System.out.println("id " + user1.getIdentifier() + ", " + user1.getName() + ", $" + user1.getBalance());
		System.out.println("id " + user2.getIdentifier() + ", " + user2.getName() + ", $" + user2.getBalance());
		System.out.println("id " + user3.getIdentifier() + ", " + user3.getName() + ", $" + user3.getBalance());
		System.out.println("id " + user4.getIdentifier() + ", " + user4.getName() + ", $" + user4.getBalance());
		System.out.println("id " + user5.getIdentifier() + ", " + user5.getName() + ", $" + user5.getBalance());
		System.out.println("id " + user6.getIdentifier() + ", " + user6.getName() + ", $" + user6.getBalance());

		

	}
}
