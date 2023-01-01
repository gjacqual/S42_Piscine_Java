package ex04;

import java.util.UUID;

public class Transaction {
	private UUID identifier;
	
	private User recipient;
	
	private User sender;
	
	private Category transferCategory;
	
	private Integer transferAmount;

	public enum Category {
		CREDITS,
		DEBITS
	}

	public Transaction(User sender, User recipient, Integer transferAmount,
                       Category transferCategory) {
		this.identifier = UUID.randomUUID();
		this.sender = sender;
		this.recipient = recipient;
		this.transferCategory = transferCategory;
		setTransferAmount(transferAmount);
	}

	public void setTransferAmount(Integer transferAmount) {
		if (transferCategory.equals(Category.DEBITS)) {
			if (transferAmount >= 0) {
				this.transferAmount = transferAmount;
			} else {
				System.out.println("Error: incorrect transaction DEBIT and (" + transferAmount + ")");
			}
		} else if (transferCategory.equals(Category.CREDITS)) {
			if (transferAmount <= 0) {
				this.transferAmount = transferAmount;
			} else {
				System.out.println("Error: incorrect transaction CREDIT and (" + transferAmount + ")");
			}
		}
	}

	public UUID getIdentifier() {
		return identifier;
	}

	public User getSender() {
		return sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public Integer getAmount() {
		return transferAmount;
	}

	public Category getTransCategory() {
		return transferCategory;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"identifier=" + identifier +
				", recipient=" + recipient +
				", sender=" + sender +
				", transferCategory=" + transferCategory +
				", transferAmount=" + transferAmount +
				'}';
	}
}
