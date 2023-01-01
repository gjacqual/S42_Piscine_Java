package ex02;

public class Program {
	public static void main(String[] args) {

		UsersList usersList = new UsersArrayList();

		User user1 = new User("Maximus", 100);
		User user2 = new User("Ivan", 500);
		User user3 = new User("Elena", 100);
		User user4 = new User("Jack", 110);
		User user5 = new User("Kate", 1);
		User user6 = new User("Macos", 0);
		User user7 = new User("7", 10);
		User user8 = new User("8", 10);
		User user9 = new User("9", 10);
		User user10 = new User("10", 10);
		User user11 = new User("11", 10);
		User user12 = new User("12", 10);
		User user13 = new User("13", 10);

		System.out.println("Check:  new users: ");
		System.out.println(user1);
		System.out.println(user2);

		usersList.addUser(user1);
		usersList.addUser(user2);
		usersList.addUser(user3);
		usersList.addUser(user4);
		usersList.addUser(user5);
		usersList.addUser(user6);
		usersList.addUser(user7);
		usersList.addUser(user8);
		usersList.addUser(user9);
		System.out.println("Check: Array true size before increase: ");
		System.out.println(((UsersArrayList) usersList).getArraySize());
		usersList.addUser(user10);
		usersList.addUser(user11);
		usersList.addUser(user12);
		usersList.addUser(user13);
		System.out.println("Check: Array true size after increase: ");
		System.out.println(((UsersArrayList) usersList).getArraySize());

		try {
			System.out.println("Check: getUserById(3): ");
			System.out.println(usersList.getUserById(3));
			System.out.println("Check: getUserByIndex(11): ");
			System.out.println(usersList.getUserByIndex(11));
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("Check: getUserById(14): ");
			System.out.println(usersList.getUserById(14));
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("Check: getUserByIndex(-1): ");
			System.out.println(usersList.getUserByIndex(-1));
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Check: getNumberOfUsers(): ");
		System.out.println(usersList.getNumberOfUsers());
	}
}
