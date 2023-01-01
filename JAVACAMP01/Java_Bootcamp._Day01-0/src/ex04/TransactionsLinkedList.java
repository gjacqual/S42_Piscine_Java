package ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private TransactionNode first;
    private TransactionNode last;
    private Integer listSize = 0;

    public TransactionNode getFirst() {
        return first;
    }

    public TransactionNode getLast() {
        return last;
    }

    public Integer getListSize() {
        return listSize;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (listSize == 0 && first == null && last == null) {
            TransactionNode newTransactionNode = new TransactionNode(transaction, null, null);
            first = newTransactionNode;
            last = newTransactionNode;
        } else {
            TransactionNode newTransactionNode = new TransactionNode(transaction, null, last);
            last.setNext(newTransactionNode);
            last = last.getNext();
        }
        listSize++;

    }

    @Override
    public void deleteTransactionById(UUID uuid) throws TransactionNotFoundException {
        TransactionNode temp = first;

       while (temp != null) {
            if(temp.getData().getIdentifier().equals(uuid)) {
                if (temp.getPrev() != null) {
                    temp.getPrev().setNext(temp.getNext());
                } else {
                    first = temp.getNext();
                }
                if (temp.getNext() != null) {
                    temp.getNext().setPrev(temp.getPrev());
                }
                listSize--;
                return;
            }
            temp = temp.getNext();
        }
        throw new TransactionNotFoundException("Transaction № " + uuid + " not found");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transactionArray = new Transaction[listSize];
        TransactionNode temp = first;

        if (temp == null) {
            return null;
        }

        for (int i = 0; i < listSize; i++) {
            transactionArray[i] = temp.getData();
            temp = temp.getNext();
        }
        return transactionArray;
    }

    private class TransactionNode {
        private Transaction data;
        private TransactionNode next;
        private TransactionNode prev;

        public TransactionNode(Transaction data, TransactionNode next, TransactionNode prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public Transaction getData() {
            return data;
        }

        public void setData(Transaction data) {
            this.data = data;
        }

        public TransactionNode getNext() {
            return next;
        }

        public void setNext(TransactionNode next) {
            this.next = next;
        }

        public TransactionNode getPrev() {
            return prev;
        }

        public void setPrev(TransactionNode prev) {
            this.prev = prev;
        }
    }

}
