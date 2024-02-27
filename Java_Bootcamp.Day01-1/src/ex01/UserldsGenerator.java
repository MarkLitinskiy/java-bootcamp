package ex01;

public class UserldsGenerator {

    private static int id;
    private static UserldsGenerator single;
    private UserldsGenerator(){
        id = 0;
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
