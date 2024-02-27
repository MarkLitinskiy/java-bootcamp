package ex05;

public class UserldsGenerator {

    private static int id;
    private static UserldsGenerator single;
    private UserldsGenerator(){
        id = 1;
    }
    public static UserldsGenerator getInstance(){
        if (single == null){
            single = new UserldsGenerator();
        }
        return single;
    }

    public static int generateId(){
        return id++;
    }
}
