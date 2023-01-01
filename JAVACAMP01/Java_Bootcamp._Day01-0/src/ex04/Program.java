package ex04;

import java.util.UUID;

public class Program {
	public static void main(String[] args) {

		UsersList usersList = new UsersArrayList();

		User user1 = new User("Maximus", 100);
		User user2 = new User("Ivan", 500);
		User user3 = new User("Elena", 100);
		User user4 = new User("Jack", 110);
		User user5 = new User("Kate", 1);
		User user6 = new User("Macos", 0);

		usersList.addUser(user1);
		usersList.addUser(user2);
		usersList.addUser(user3);
		usersList.addUser(user4);
		usersList.addUser(user5);
		usersList.addUser(user6);

		System.out.println("Check: create TransactionLIst");
		TransactionsList transactionsList = new TransactionsLinkedList();

		Transaction transaction1 = new Transaction(user1, user2, 50, Transaction.Category.DEBITS);
		Transaction transaction2 = new Transaction(user1, user2, 40, Transaction.Category.DEBITS);
		Transaction transaction3 = new Transaction(user1, user2, 10, Transaction.Category.DEBITS);

		System.out.println("Check: addTransactions");
		transactionsList.addTransaction(transaction1);
		transactionsList.addTransaction(transaction2);
		transactionsList.addTransaction(transaction3);

		System.out.println("Check: list of Transaction to Array");
		Transaction[] transactionsArray1 = transactionsList.toArray();

		for (int i = 0; i < transactionsArray1.length; i++) {
			System.out.println(transactionsArray1[i]);
		}

		System.out.println("Check: deleteTransactionById");
		try {
			transactionsList.deleteTransactionById(transactionsArray1[2].getIdentifier());
		} catch (TransactionNotFoundException e) {
			System.out.println(e.getMessage());
		}
		Transaction[] transactionsArray2 = transactionsList.toArray();
		for (int i = 0; i < transactionsArray2.length; i++) {
			System.out.println(transactionsArray1[i]);
		}


		System.out.println("Check: Exception for deleteTransactionById");
		try {
			transactionsList.deleteTransactionById(UUID.randomUUID());
		} catch (TransactionNotFoundException e) {
			System.out.println(e.getMessage());
		}





	}
}
