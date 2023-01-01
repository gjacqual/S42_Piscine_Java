package ex04;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);

    void deleteTransactionById(UUID uuid) throws TransactionNotFoundException;

    Transaction[] toArray();
}
