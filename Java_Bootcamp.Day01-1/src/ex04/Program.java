package ex04;

import java.util.UUID;

public class Program {
    public static void main(String[] args) throws Exception {
        TransactionsService facade = new TransactionsService();

        User Mark = new User("Mark", 500);
        User Aboba = new User("Aboba", 1000);

        facade.addUser(Mark);
        facade.addUser(Aboba);

        System.out.print("Mark balance: ");
        System.out.println(facade.retrievingUserBalance(Mark));
        System.out.print("Aboba balance: ");
        System.out.println(facade.retrievingUserBalance(Aboba));

        UUID transactionID = facade.transferTransaction(Mark.getId(), Aboba.getId(), 50);
        facade.transferTransaction(Aboba.getId(), Mark.getId(), 100);

        System.out.print("Mark balance: ");
        System.out.println(facade.retrievingUserBalance(Mark));
        System.out.print("Aboba balance: ");
        System.out.println(facade.retrievingUserBalance(Aboba));

        Transaction[] transactions = facade.retrievingTransfers(1);

        for (Transaction transaction : transactions) {
            System.out.println(transaction.getId());
        }

        facade.removeTransaction(transactionID, Mark.getId());

        transactions = facade.checkValidityOfTransactions(Aboba);

        for (Transaction transaction : transactions) {
            System.out.println(transaction.getId());
        }
    }
}
