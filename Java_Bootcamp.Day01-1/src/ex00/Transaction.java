package ex00;

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

    public Transaction(User recipient, User sender, Category transferCategory, double transferAmount) throws Exception {
        setId();
        setRecipient(recipient);
        setSender(sender);
        setTransferCategory(transferCategory);
        setTransferAmount(transferAmount);
        transactionInit(recipient, sender, this.transferAmount);
    }

    public UUID getId(){
        return id;
    }

    public void setId(){
        id = UUID.randomUUID();
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
                this.sender = recipient;
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
        try{
            if (transferCategory == Category.DEBIT && transferAmount >= 0 || transferCategory == Category.CREDIT && transferAmount < 0)
                this.transferAmount = transferAmount;
            else
                throw new Exception("Транзакция должна соответствовать типу операции");
        } catch (Exception e) {
            System.out.println("Транзакция должна соответствовать типу операции!!!");
            this.transferAmount = 0;
        }
    }

    private void transactionInit(User recipient, User sender, double transferAmount) throws Exception {
        try {
            if (sender.getBalance() >= transferAmount) {
                sender.setBalance(sender.getBalance() - transferAmount);
                recipient.setBalance(recipient.getBalance() + transferAmount);
            } else {
                throw new Exception("У отправителя недостаточно средств");
            }
        } catch (Exception e) {
            System.out.println("У отправителя недостаточно средств!");
        }

    }
}
