package ex05;

public class User {
    private int id;
    private String name;
    private double balance;

    public TransactionsLinkedList list;

    UserldsGenerator UserIdsGenerator;

    public User(String name, double balance){
        setId(id);
        setName(name);
        setBalance(balance);
        list = new TransactionsLinkedList();
    }

    public int getId(){
        return id;
    }

    private void setId(int inComeId){
        id = UserIdsGenerator.getInstance().generateId();
    }

    public String getName(){
        return name;
    }

    public void setName(String inComeName){
        name = inComeName;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double inComeBalance) {
        balance = inComeBalance;
    }

    public TransactionsLinkedList getList(){
        return list;
    }

    public void setList(Transaction transaction){
        list.addTransaction(transaction);
    }

}
