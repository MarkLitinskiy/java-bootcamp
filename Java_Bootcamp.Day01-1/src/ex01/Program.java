package ex01;

public class Program {
    public static void main(String[] args) {
        User Mark = new User("Mark", 100);
        User Alex = new User("Alex", 200);
        User Misha = new User("Misha", 300);

        System.out.printf("%d %s\n", Mark.getId(), Mark.getName());
        System.out.printf("%d %s\n", Alex.getId(), Alex.getName());
        System.out.printf("%d %s\n", Misha.getId(), Misha.getName());
    }
}
