package ex00;

public class User {
    private int id;
    private String name;
    private double balance;

    public User(int id, String name, double balance) throws Exception {
        setId(id);
        setName(name);
        setBalance(balance);
    }

    public int getId(){
        return id;
    }

    public void setId(int inComeId){
        id = inComeId;
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

    public void setBalance(double inComeBalance) throws Exception {
        try {
            if (balance >= 0 && inComeBalance >= 0)
                balance = inComeBalance;
            else
                throw new Exception("Баланс не может быть отрицательным");
        } catch (Exception e) {
            System.out.println("Баланс не может быть отрицательным!!!");
            System.exit(-1);
        }
    }
}
