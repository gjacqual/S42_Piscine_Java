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


	
}
