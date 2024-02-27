package ex03;

import java.util.UUID;

public class Program  {

    public static void main(String[] args) throws Exception {
        User Mark = new User("Mark", 1000);
        User Oleg = new User("Oleg", 1000);

        Transaction [] array;

        Transaction one = new Transaction(Mark, Oleg, Transaction.Category.DEBIT, 100);
        Transaction two = new Transaction(Oleg, Mark, Transaction.Category.CREDIT, -100);
        Transaction three = new Transaction(Oleg, Mark, Transaction.Category.DEBIT, 50);
        Transaction four = new Transaction(Mark, Oleg, Transaction.Category.CREDIT, -50);
        Transaction five = new Transaction(Mark, Oleg, Transaction.Category.CREDIT, -50);

        Mark.setList(one);
        Mark.setList(two);
        Mark.setList(three);
        Mark.setList(four);

        System.out.println(Mark.list.countTransactions);
        array = Mark.list.toArray();

        for (Transaction value : array) {
            System.out.print(value);
            System.out.print(" ");
        }
        System.out.println();

        Mark.list.removeTransactionByID(two);

        System.out.println(Mark.list.countTransactions);
        array = Mark.list.toArray();

        for (Transaction transaction : array) {
            System.out.print(transaction);
            System.out.print(" ");
        }
        System.out.println();

        Mark.list.removeTransactionByID(five);
    }
}
