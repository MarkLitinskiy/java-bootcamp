package ex05;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeTransactionByID(UUID transactionID);
    Transaction[] toArray();
}
