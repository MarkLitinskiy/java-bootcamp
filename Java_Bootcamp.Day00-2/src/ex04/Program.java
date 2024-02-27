package ex04;

import java.util.Scanner;

public class Program {
    public static void printGraph(int [][] strCount) {
        int max = strCount[0][1];
        boolean [] checkFirst = new boolean[10];
        for (int i = 10; i >= 0; --i) {
            for (int j = 0; j <= 9; ++j){
                if (strCount[j][1] * 10 / max >= i && !checkFirst[j]){
                    checkFirst[j] = true;
                    int temp = strCount[j][1];
                    if (temp < 10)
                        System.out.print("  ");
                    else if (temp > 10 && temp < 100)
                        System.out.print(" ");
                    System.out.printf("%d", strCount[j][1]);
                } else if (strCount[j][1] * 10 / max >= i)
                    System.out.print("  #");
                else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        for (int i = 0; i <= 9; ++i) {
            if (strCount[i][0] == '\0')
                System.out.print("  -");
            else
                System.out.printf("  %c", strCount[i][0]);
        }
    }

    public static int [][] sortForLexic(int [][] strCount) {
        int temp = 0;
        for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (strCount[j][1] == strCount[j + 1][1] &&
                        strCount[j][0] > strCount[j + 1][0] &&
                        strCount[j][0] != 0 &&
                        strCount[j+1][0] != 0) {
                            temp = strCount[j][0];
                            strCount[j][0] = strCount[j + 1][0];
                            strCount[j + 1][0] = temp;
                    }
                }
            }
        return strCount;
    }

    public static int [][] getTopMax(int [][] strCount) {
        int [][] topMax = new int[10][2];
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                topMax [i][j] = 0;
            }
        }
        int tempMax = Integer.MAX_VALUE;

        for (int i = 0; i <= 9; i++){
            for (int j = 0; strCount[j][0] != '\n'; j++){
                if (strCount[j][1] >= topMax[i][1] && strCount[j][1] <= tempMax){
                    for (int k = 0; k <= i; k++){
                        if (topMax[k][1] == strCount[j][1] && topMax[k][0] == strCount[j][0]) {
                            break;
                        } else if (k == i){
                            topMax[i][1] = strCount[j][1];
                            topMax[i][0] = strCount[j][0];
                        }
                    }
                }
            }
            tempMax = topMax[i][1];
        }
        sortForLexic(topMax);
        return topMax;
    }

    public static void main(String[] args){
        String str;
        Scanner in = new Scanner(System.in);
        int [][] strCount = new int [1000][2];
        strCount[0][0] = '\n';

        str = in.nextLine();
        if (str.equals(""))
            System.exit(0);
        for (char ch : str.toCharArray()) {
            for (int i = 0; ; i++){
                if(strCount[i][0] == ch){
                    strCount[i][1]++;
                    break;
                } else if (strCount[i][0] == '\n'){
                    strCount[i][0] = ch;
                    strCount[i][1] = 1;
                    strCount[i+1][0] = '\n';
                    break;
                }
            }
        }
        strCount = getTopMax(strCount);
        printGraph(strCount);
    }
}
