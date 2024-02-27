package ex01;
import java.util.Scanner;

public class Program {
    public static void main (String [] args){
        Scanner in = new Scanner(System.in);
        int num = in.nextInt(), steps = 0;

        if (num <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }

        for (steps = 2; steps <= Math.sqrt(num); steps++) {
            if (num % steps == 0) {
                System.out.printf("false %d", steps - 1);
                System.exit(0);
            }
        }

        System.out.printf("true %d", steps - 1);
        System.exit(0);
    }
}
