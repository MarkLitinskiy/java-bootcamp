package ex00;

import java.io.*;
import java.util.*;

public class Program {

    private static Map <String, String> signal = new HashMap<>();
    private static Vector <String> out = new Vector<>();

    /**
     * Записывает в мапу пару значений расширение:сигнатура_файла
     * @param line строка из файла вида: расширение, сигнатура
     * @return очищенная строка
     */
    public static String writeToMap(String line){
        if(line.charAt(line.length() - 1) == '\n') {
            line = line.substring(0, line.length() - 1);
        }
        String[] parts = line.split(",");
        if (parts.length == 2) {
            parts[1] = parts[1].replaceAll("\\s", "");
            signal.put(parts[0], hexToString(parts[1]));
            line = "";
        }
        return line;
    }

    /**
     * переводит шестнадцатеричную запись в обычную строку
     * @param hex строка с шестнадцатеричной записью
     */
    public static String hexToString(String hex) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String hexByte = hex.substring(i, i + 2);
            int intValue = Integer.parseInt(hexByte, 16);
            result.append((char) intValue);
        }
        return result.toString();
    }

    /**
     * Чтение файла signal.txt в потоке посимвольно
     */
    public static void readSignalFile() {
        try {
            FileInputStream fileSignalStream = new FileInputStream("src/ex00/signal.txt");
            InputStreamReader fileSignalReader = new InputStreamReader(fileSignalStream);
            int data;
            String line = "";
            while ((data = fileSignalReader.read()) != -1) {

                line += (char) data;
                if ((char) data == '\n') {
                    line = writeToMap(line);
                }
            }
            writeToMap(line);
            fileSignalStream.close();
            fileSignalReader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void checkTypeFile(String path) {
        try {
            File file = new File(path); // Создаем объект File на основе пути к файлу
            if (!file.exists()) {
                throw new IOException ("Файла не существует");
            }
            FileInputStream fileSignalStream = new FileInputStream(path);
            InputStreamReader fileSignalReader = new InputStreamReader(fileSignalStream);
            byte[] buffer = new byte[10];
            fileSignalStream.read(buffer, 0, 10);
            int index = 0;
            for (Map.Entry<String, String> entry : signal.entrySet()) {
                String name = entry.getKey(), score = entry.getValue();
                String stringBuffer = new String(buffer, "UTF-8");
                index = stringBuffer.indexOf(score);
                if (index != -1) {
                    out.add(name);
                    break;
                }
            }
            if (index == -1){
                out.add("UNDEFINED");
            }
            fileSignalStream.close();
            fileSignalReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFiles() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        while (!str.equals("42")){
            checkTypeFile(str);
            System.out.println("PROCESSED");
            str = in.nextLine();
        }
    }

    private static void printFiles(){
        for (String value : out){
            System.out.println(value);
        }
    }

    public static void main(String[] args){
        readSignalFile();
        readFiles();
        printFiles();
    }
}
