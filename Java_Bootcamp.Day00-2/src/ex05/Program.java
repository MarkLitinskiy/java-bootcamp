package ex05;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String [] september = {"TU", "WE","TH", "FR", "SA", "SU", "MO",
                "TU", "WE","TH", "FR", "SA", "SU", "MO", "TU", "WE", "TH",
                "FR", "SA", "SU", "MO", "TU", "WE", "TH", "FR", "SA", "SU",
                "MO", "TU", "WE"};
        String [] names = new String[10];
        String [][] classes = new String[10][2];
        String [][] attendance = new String[100][4];
        String [][] timetable = new String[100][2];
        String currentLine;
        int countClasses = 0;
        int maxName = 0;

        currentLine = in.nextLine();
        for (int i = 0; !currentLine.equals("."); ++i){
            names[i] = currentLine;
            if(names[i].length() > maxName)
                maxName = names[i].length();
            currentLine = in.nextLine();
        }

        currentLine = in.next();
        for (int i = 0; !currentLine.equals("."); ++i){
            for (int j = 0; j < 2; ++j){
                classes[i][j] = currentLine;
                currentLine = in.next();
            }
        }

        currentLine = in.next();
        for (int i = 0; !currentLine.equals("."); ++i){
            for (int j = 0; j < 4; ++j){
                attendance[i][j] = currentLine;
                currentLine = in.next();
            }
        }
        for (int i = 0; i < maxName; ++i)
            System.out.print(" ");
        for (int i = 0; i < 30; ++i) {
            for (int j = 0; j < classes.length && classes[j][0] != null; ++j) {
                if (september[i].equals(classes[j][1])) {
                    System.out.printf("%s:00 %s  %d|", classes[j][0], classes[j][1], i + 1);
                    timetable[countClasses][0] = classes[j][0];
                    timetable[countClasses][1] = "" + (i + 1);
                    ++countClasses;
                }
            }
        }
        System.out.println();
        for (int i = 0; i < names.length && names[i] != null ; ++i){
            System.out.print(names[i]);
            for (int j = 0; j < countClasses; ++j) {
                boolean flag = false;
                    if(j == 0){
                        for (int g = 0; g <= 7 + maxName - names[i].length(); ++g)
                            System.out.print(" ");
                    } else {
                        for (int g = 0; g <= 7; ++g)
                            System.out.print(" ");
                    }
                    
                if(timetable[j][1].length() == 2)
                    System.out.print(" ");
                for (int k = 0; k < attendance.length && attendance[k][0] != null; ++k) {
                    if (attendance[k][0].equals(names[i]) && attendance[k][1].equals(timetable[j][0]) && attendance[k][2].equals(timetable[j][1])) {
                        if (attendance[k][3].equals("HERE")) {
                            System.out.print(" 1|");
                        } else if (attendance[k][3].equals("NOT_HERE")) {
                            System.out.print("-1|");
                        }
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.print("  |");
                }
            }
            System.out.println();
        }
    }
}
