package ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    private static Scanner in = new Scanner(System.in);
    private static String currentCommand = "";
    private static String currentDirectory = "";

    private static void commandLs() throws IOException {
        if(currentCommand.length() != 2){
            throw new IOException("Некорректная команда ls");
        }
        File dir = new File(currentDirectory);
        if (dir.exists()) {
            File[] filesAndDirectories = dir.listFiles();
            for (File currentFile : filesAndDirectories){
                long size = Files.size(Paths.get(currentFile.getAbsolutePath()))/1024;
                System.out.println(currentFile.getName() + " " + size + " Kb");
            }
        } else {
            throw new IOException("Директории не существует");
        }
    }

    private static void commandMv() throws IOException {
        String [] words = currentCommand.split(" ");
        File source = new File(currentDirectory + "/" + words[1]);
        if(source.exists())
        {
            File target;
            if (words[2].contains("/")) {
                target = new File(currentDirectory + "/" + words[2] + "/" + words[1]);
            } else {
                target = new File(currentDirectory + "/" + words[2]);
            }
            if(!source.renameTo(target)){
                throw new IOException("Процедуру перемещения/переименования не удалось завершить успешно");
            }
        } else {
            throw new IOException("Директории не существует");
        }
    }

    private static void commandCd() throws IOException {
        String newPath = currentCommand.substring(3);;
        if(currentCommand.charAt(3) != '/') {
            newPath = currentDirectory + "/" + newPath;
        }
        Path path = Paths.get(newPath);
        path = path.normalize();
        File dir = path.toFile();
        if(dir.exists() && dir.isDirectory()){
            currentDirectory = path.toAbsolutePath().toString();
            System.out.println(currentDirectory);
        } else {
            throw new IOException("Директории не существует");
        }
    }

    public static void main(String[] args) {
        currentDirectory = args[0].substring(17);
        System.out.println(currentDirectory);
        while(!(currentCommand = in.nextLine()).equals("exit")){
            try {
                if (currentCommand.length() >= 2){
                    switch (currentCommand.substring(0, 2)) {
                        case ("ls") -> commandLs();
                        case ("mv") -> commandMv();
                        case ("cd") -> commandCd();
                        default -> System.out.println("ОШИБКА!!! ОШИБКА!!! ОШИБКА!!!");
                    }
            }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
