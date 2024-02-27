package ex05;

import java.util.UUID;

public class TransactionsService {
    UsersArrayList userList = new UsersArrayList();

    public int addUser(String name, double balance){
        userList.addUser(name, balance);
        return userList.retrieveNumberOfUsers();
    }

    public int addUser(User newUser){
        userList.addUser(newUser);
        return userList.retrieveNumberOfUsers();
    }

    public double retrievingUserBalance(User user){
        return user.getBalance();
    }

    public UUID transferTransaction(int idRecipient, int idSender, double transferAmount) throws Exception {
        UUID id = UUID.randomUUID();
        Transaction recipientTransaction = new Transaction(id, userList.retrieveUserByID(idRecipient), userList.retrieveUserByID(idSender), Transaction.Category.DEBIT, transferAmount);
        Transaction senderTransaction = new Transaction(id, userList.retrieveUserByID(idRecipient), userList.retrieveUserByID(idSender), Transaction.Category.CREDIT, transferAmount);

        userList.retrieveUserByID(idRecipient).list.addTransaction(recipientTransaction);
        userList.retrieveUserByID(idSender).list.addTransaction(senderTransaction);
        return id;
    }

    public Transaction[] retrievingTransfers(User user){
        return user.list.toArray();
    }

    public Transaction[] retrievingTransfers(int userID){
        return userList.retrieveUserByID(userID).list.toArray();
    }

    public void removeTransaction(UUID transaction, int userID){
        userList.retrieveUserByID(userID).list.removeTransactionByID(transaction);
    }

    public Transaction[] checkValidityOfTransactions (User user){
        TransactionsLinkedList listUnvalid = new TransactionsLinkedList();
        Transaction[] temp;
        Transaction[] transactions = user.list.toArray();


        for (int i = 0; i < user.list.countTransactions; i++){
            int countSecondTransactions;
            boolean found = false;
            if (transactions[i].getTransferCategory() == Transaction.Category.CREDIT){
                countSecondTransactions = transactions[i].getRecipient().list.countTransactions;
                temp = transactions[i].getRecipient().list.toArray();
            } else {
                countSecondTransactions = transactions[i].getSender().list.countTransactions;
                temp = transactions[i].getSender().list.toArray();
            }
            for(int j = 0; j < countSecondTransactions; j++){
                if (transactions[i].getId() == temp[j].getId()) {
                    found = true;
                    break;
                }
            }
            if (!found){
                listUnvalid.addTransaction(transactions[i]);
            }
        }
        return listUnvalid.toArray();
    }
}
