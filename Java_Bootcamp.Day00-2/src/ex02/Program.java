package ex02;
import java.util.Scanner;

public class Program {

    public static int findSum(int number) {
        int sum = 0;

        while (number > 0) {
                sum += number % 10;
                number /= 10;
           }
        return sum;
    }

    public static boolean isSimple(int sum) {
        for (int steps = 2; steps <= Math.sqrt(sum); steps++) {
            if (sum % steps == 0) {
                return false;
            }
        }
       return true;
    }

    public static void main (String [] args){
        Scanner in = new Scanner(System.in);
        int coffeeCount = 0, currentNumber = in.nextInt(), sum = 0;

        while (currentNumber != 42) {
            if (isSimple(findSum(currentNumber)))
                coffeeCount++;
            currentNumber = in.nextInt();
        }
        System.out.printf("Count of coffee-request – %d", coffeeCount);
    }
}
