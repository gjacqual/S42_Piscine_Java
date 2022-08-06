package ex00;

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

	public Transaction(User sender, User recipient, Integer transferAmount) {
		this.identifier = UUID.randomUUID();
		this.recipient = recipient;
		this.sender = sender;
		if (transferAmount >= 0) {
			transferCategory = Category.DEBITS;
		} else {
			transferCategory = Category.CREDITS;
		}
		setTransferAmount(transferAmount);
		if (sender.getBalance() < transferAmount) {
			System.out.println("Not enough credits to make a transfer");
		} else {
			sender.setBalance(sender.getBalance() - transferAmount);
			recipient.setBalance(recipient.getBalance() + transferAmount);
		}
	}

	public void setTransferAmount(Integer transferAmount) {
		if (transferCategory.equals(Category.DEBITS) && (transferAmount > 0)) {
			this.transferAmount = transferAmount;
		} else if (transferCategory.equals(Category.CREDITS) && (transferAmount < 0)) {
			this.transferAmount = transferAmount;
		} else {
			System.out.println("Error: incorrect transaction.");
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
}
