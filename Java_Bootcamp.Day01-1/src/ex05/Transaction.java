package ex05;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;

    private Category transferCategory;
    private double transferAmount;

    enum Category {
        DEBIT, CREDIT
    }

    public static class IllegalTransactionException extends RuntimeException {
        public IllegalTransactionException(String errorMessage) {
            super(errorMessage);
        }
    }

    public Transaction(UUID id, User recipient, User sender, Category transferCategory, double transferAmount) throws Exception {
        setId(id);
        setRecipient(recipient);
        setSender(sender);
        setTransferCategory(transferCategory);
        setTransferAmount(transferAmount);
        transactionInit(recipient, sender, transferCategory, this.transferAmount);
    }

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public User getRecipient(){
        return recipient;
    }

    public void setRecipient(User recipient){
        try{
            if (recipient.getBalance() >= 0)
                this.recipient = recipient;
            else
                throw new Exception("Баланс получателя не может быть отрицательным");
        } catch (Exception e) {
            System.out.println("Баланс получателя не может быть отрицательным!!!");
        }
    }

    public User getSender(){
        return sender;
    }

    public void setSender(User sender){
        try{
            if (sender.getBalance() >= 0)
                this.sender = sender;
            else
                throw new Exception("Баланс отправителя не может быть отрицательным");
        } catch (Exception e) {
            System.out.println("Баланс отправителя не может быть отрицательным!!!");
        }
    }

    public Category getTransferCategory(){
        return transferCategory;
    }

    public void setTransferCategory(Category transferCategory){
        this.transferCategory = transferCategory;
    }

    public double getTransferAmount(){
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount){
        this.transferAmount = transferAmount;
    }

    private void transactionInit(User recipient, User sender, Category transferCategory, double transferAmount) throws Exception {
                if (sender.getBalance() >= transferAmount && transferCategory == Category.CREDIT) {
                    sender.setBalance(sender.getBalance() - transferAmount);
                } else if (sender.getBalance() >= transferAmount && transferCategory == Category.DEBIT) {
                    recipient.setBalance(recipient.getBalance() + transferAmount);
                } else {
                    throw new IllegalTransactionException("У отправителя недостаточно средств");
                }
            }
    }
