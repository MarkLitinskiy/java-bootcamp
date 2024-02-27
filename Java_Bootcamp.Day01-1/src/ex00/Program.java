package ex00;


public class Program {
    public static void main (String []args) throws Exception {
        User Mark = new User(0, "Mark", 50);
        User Alex = new User(1, "Alex", 100);

        System.out.printf("%s: %f\n", "Mark", Mark.getBalance());
        System.out.printf("%s: %f\n", "Alex", Alex.getBalance());

        Transaction AlexToMark = new Transaction(Mark, Alex, Transaction.Category.DEBIT, 50);

        System.out.printf("%s: %f\n", "Mark", Mark.getBalance());
        System.out.printf("%s: %f\n", "Alex", Alex.getBalance());

        Transaction MarkToAlex = new Transaction(Mark, Alex, Transaction.Category.CREDIT, -50);
    }
}
