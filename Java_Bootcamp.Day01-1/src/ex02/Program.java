package ex02;

public class Program {
    public static void main(String[] args) {
        UsersArrayList list = new UsersArrayList();
        list.addUser("Mark");
        list.addUser("Ivan");
        list.addUser("Nikita");
        list.addUser("Maks");

        System.out.println(list.retrieveUserByID(1));
        System.out.println(list.retrieveUserByIndex(0));
        System.out.println(list.retrieveNumberOfUsers());

        list.addUser("Maks");
        list.addUser("Maks");
        list.addUser("Maks");
        list.addUser("Maks");
        list.addUser("Maks");
        list.addUser("Maks");
        list.addUser("Maks");
        list.addUser("Maks");
        list.addUser("Maks");

        System.out.println(list.retrieveNumberOfUsers());
        System.out.println(list.retrieveUserByID(20));

    }
}
