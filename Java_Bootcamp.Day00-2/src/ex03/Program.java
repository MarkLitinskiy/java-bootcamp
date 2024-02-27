package ex03;

import java.util.Scanner;

public class Program {

    public static long reverse(long graphParameter){
        long reverse_graphParameter = 0;

        while (graphParameter != 0){
            reverse_graphParameter = reverse_graphParameter * 10 + (graphParameter % 10);
            graphParameter /= 10;
        }
        return reverse_graphParameter;
    }
    public static void printGraph(long graphParameter){
        int weekCount = 1;
        graphParameter = reverse(graphParameter);
        while (graphParameter != 0) {
            System.out.print("Week " + weekCount + " ");
            for (int i = 0; i < graphParameter % 10; i++){
                System.out.print("=");
            }
            System.out.println(">");
            graphParameter /= 10;
            ++weekCount;
        }
    }

    public static void main (String[] args){
        int minGrade = 10, temp = 0;
        Scanner in = new Scanner(System.in);
        String currentLine;
        long graphParameter = 0;
        for (int i = 1; i <= 18; ++i){
            currentLine = in.nextLine();
            if (currentLine.equals("42")) {
               break;
            }
            if (!currentLine.equals("Week " + i)) {
                System.out.println("IllegalArgument");
                System.exit(-1);
            } else {
                for (int j = 0; j < 5; ++j){
                    temp = in.nextInt();
                    if (temp >= 1 && temp <= 9 && temp < minGrade){
                        minGrade = temp;
                    }
                }
                graphParameter = graphParameter * 10 + minGrade;
                minGrade = 10;
                in.nextLine();
            }
        }
        printGraph(graphParameter);
        System.exit(0);
    }
}
