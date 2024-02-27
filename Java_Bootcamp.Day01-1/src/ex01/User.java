package ex01;

public class User {
    private int id;
    private String name;
    private double balance;

    UserldsGenerator UserIdsGenerator;

    public User(String name, double balance){
        setId(id);
        setName(name);
        setBalance(balance);
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
}
