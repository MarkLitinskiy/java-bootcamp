package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Program {

    static Scanner in = new Scanner(System.in);
    static TransactionsService facade = new TransactionsService();
    public static void printDevMenu(){
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV – remove a transfer by ID");
        System.out.println("6. DEV – check transfer validity");
        System.out.println("7. Finish execution");
        System.out.println("-----------------------------------------------");
    }

    public static void printProductMenu(){
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. Finish execution");
        System.out.println("-----------------------------------------------");
    }

    public static void addUser(){
        System.out.println("Enter a user name and a balance");
        try {
            String name = in.next();
            int balance = in.nextInt();
            facade.addUser(name, balance);
        }catch(Exception e) {
            System.err.println("Произошло исключение: " + e.getMessage());
        }
    }

    public static void viewBalance(){
        System.out.println("Enter a user ID");
        try {
        int id = in.nextInt();
            double balance = facade.retrievingUserBalance(facade.userList.retrieveUserByID(id));
            System.out.println(balance);
        } catch (Exception e){
            System.err.println("Произошло исключение: " + e.getMessage());
        }
    }

    public static void performTransfer() throws Exception {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");

        try {
            int idSender = in.nextInt();
            int idRecipient = in.nextInt();
            double transferAmount = in.nextInt();
            facade.transferTransaction(idRecipient, idSender, transferAmount);
            System.out.println("The transfer is completed");
        } catch (Exception e){
            System.err.println("Произошло исключение: " + e.getMessage());
        }
    }

    public static void viewAllTransactions() {
        System.out.println("Enter a user ID");
        try {
            int idPerson = in.nextInt();
            Transaction[] transactions = facade.retrievingTransfers(idPerson);
            for (Transaction transaction : transactions) {
                String transactionOutput = "To " + transaction.getRecipient().getName() + "( id = " + transaction.getRecipient().getId() +
                        ") " + transaction.getTransferAmount() + " with id = " + transaction.getId();
                System.out.println(transactionOutput);
            }
        } catch (Exception e) {
            System.err.println("Произошло исключение: " + e.getMessage());
        }
    }

    public static void removeTransfer() {
        System.out.println("Enter a user ID and a transfer ID");
        int userID = in.nextInt();
        String transferID = in.next();
        UUID transID = UUID.fromString(transferID);
        try {
            facade.removeTransaction(transID, userID);
        } catch (UsersArrayList.UserNotFoundException | Transaction.IllegalTransactionException e) {
            System.err.println("Произошло исключение: " + e.getMessage());
        }
    }

    public static void checkTransferValidity(){
        System.out.println("Check results:");
        try {
            for (int i = 0; i < facade.userList.retrieveNumberOfUsers(); i++) {
                Transaction[] transactionsFailed = facade.checkValidityOfTransactions(facade.userList.retrieveUserByIndex(i));
                for (Transaction transaction : transactionsFailed) {
                    System.out.println(transaction.getRecipient().getName() + "(id = " + transaction.getRecipient().getId() +
                            ") has an unacknowledged transfer id = " + transaction.getId() + " from " +
                            transaction.getSender().getName() + "(id = " + transaction.getSender().getId() + ") for " + transaction.getTransferAmount());
                }
            }
        } catch (Transaction.IllegalTransactionException | UsersArrayList.UserNotFoundException e){
            System.err.println("Произошло исключение: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        int mode = 0;

        if(args[0].equals("--profile=dev")) {
            mode = 1;
        } else if (args[0].equals("--profile=production")){
            mode = 2;
        } else {
            System.out.println("Error argument!");
            System.exit(1);
        }

        while (true){
            if (mode == 1){
                printDevMenu();
            }else {
                printProductMenu();
            }
            String currentchoice = in.next();
            if(currentchoice.equals("7") && mode == 1 || currentchoice.equals("5") && mode == 2){
                break;
            } else if (currentchoice.equals("1")){
                addUser();
            } else if (currentchoice.equals("2")) {
                viewBalance();
            } else if (currentchoice.equals("3")) {
                performTransfer();
            } else if (currentchoice.equals("4")) {
                viewAllTransactions();
            } else if (currentchoice.equals("5") && mode == 1) {
                removeTransfer();
            } else if (currentchoice.equals("6") && mode == 1){
                checkTransferValidity();
            } else {
                System.out.println("Input error!");
            }
        }
    }
}
