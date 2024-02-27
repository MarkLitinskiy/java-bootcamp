package ex01;

import java.io.*;
import java.util.*;

public class Program {
    static String pathOne;
    static String pathTwo;
    /** Вектор, содержащий все встречающиеся слова */
    static Vector <String> dictionary = new Vector<>();
    /** Векторы, содержающие текст файлов */
    static Vector <String> firstFile = new Vector<>();
    static Vector <String> secondFile = new Vector<>();
    /** Векторы, содержащие количество слов в каждом файле */
    static Vector <Integer> vectorCounterOne = new Vector<>();
    static Vector <Integer> vectorCounterTwo = new Vector<>();

    /**
     * Метод считывания файлов
     * @param path Путь файлов
     * @return Вектор, содержащий текст
     * @throws IOException
     */
    private static Vector <String> scanFile(String path) throws IOException {
            Vector <String> file = new Vector<>();
            if (path != null) {
                FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader(fr);
                Scanner scanner = new Scanner(br);

                while (scanner.hasNext()) {
                    String currentWord = scanner.next();
                    file.add(currentWord);
                    boolean checkWord = false;
                    for(String i : dictionary) {
                        if(i.equals(currentWord)) {
                            checkWord = true;
                            break;
                        }
                    }
                    if (!checkWord){
                        dictionary.add(currentWord);
                    }
                }
                fr.close();
                br.close();
                scanner.close();
            }
            return file;
    }

    /**
     * Создание файла со словарём
     * @throws IOException
     */
    private static void createDictionaryFile() throws IOException {
        FileWriter fr = new FileWriter("./src/ex01/dictionary.txt");
        BufferedWriter br = new BufferedWriter(fr);
        StringBuilder dictionaryStr = new StringBuilder();
        for(String currentWord : dictionary){
            dictionaryStr.append(currentWord).append(", ");
        }

        br.write(dictionaryStr.substring(0, dictionaryStr.length() - 2));
        br.close();
        fr.close();
    }

    /**
     * Заполнение векторов с количеством слов в каждом файле
     */
    private static void fillVectors(){
        for (String wordFromDictionary : dictionary){
            int countInFirstFile = 0, countInSecondFile = 0;
            for (String wordFromFile : firstFile){
                if(wordFromFile.equals(wordFromDictionary)){
                    ++countInFirstFile;
                }
            }

            for (String wordFromFile : secondFile){
                if(wordFromFile.equals(wordFromDictionary)){
                    ++countInSecondFile;
                }
            }
            vectorCounterOne.add(countInFirstFile);
            vectorCounterTwo.add(countInSecondFile);
        }
    }

    private static double takeDenominator(){
        double firstFileModule = 0;
        double secondFileModule = 0;
        for (int current : vectorCounterOne){
            firstFileModule += current*current;
        }

        for (int current : vectorCounterTwo){
            secondFileModule += current*current;
        }
        return Math.sqrt(firstFileModule) * Math.sqrt(secondFileModule);
    }

    private static void printSimilarity(){
        int numerator = 0;
        double similarity = 0;

        for(int i = 0; i < vectorCounterOne.size(); i++){
            numerator+=vectorCounterOne.elementAt(i) * vectorCounterTwo.elementAt(i);
        }

        similarity = numerator / takeDenominator();
        String formattedDoubleSimilarity = String.format("%.3f", similarity);
        System.out.println("Similarity = " + formattedDoubleSimilarity);
    }
    public static void main(String[] args) {
        pathOne = args[0];
        pathTwo = args[1];
        try {
            firstFile = scanFile(pathOne);
            secondFile = scanFile(pathTwo);
            createDictionaryFile();
            fillVectors();
            printSimilarity();
        } catch (IOException e) {
        e.printStackTrace();
    }

    }

}
