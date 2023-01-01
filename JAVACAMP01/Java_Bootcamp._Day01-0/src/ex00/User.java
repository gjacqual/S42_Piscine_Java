package ex00;

public class User {
	private Integer identifier;
	private String name;
	private Integer balance;

	public User(Integer identifier, String name, Integer balance) {
		this.identifier = identifier;
		this.name = name;
		setBalance(balance);
	}

	public void setBalance(Integer balance) {
		if (balance < 0) {
			System.err.println("Error: You can't set a negative balance! (" + balance + ")");
			this.balance = 0;
		} else {
			this.balance = balance;
		}
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public String getName() {
		return name;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"identifier=" + identifier +
				", name='" + name + '\'' +
				", balance=" + balance +
				'}';
	}
}
