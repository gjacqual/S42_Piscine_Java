package ex01;

public class UserIdsGenerator {
	private static final UserIdsGenerator instance = new UserIdsGenerator();
	
	private static int id = 0;

	public int generateId() {
		id++;
		if (id > Integer.MAX_VALUE) {
			System.err.println("Error: The limit of ID has been reached");
			id = 0;
		}
		return id;
	}
	
	public static UserIdsGenerator getInstance() {
		return instance;
	}

}
