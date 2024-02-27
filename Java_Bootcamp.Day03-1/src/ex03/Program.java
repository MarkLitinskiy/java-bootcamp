package ex03;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

public class Program {

    static final int ANOTHER_URL = 0;
    private static int threadCounts;
    private static int countDownloadingFiles;
    static List<String> listOfURLS = new ArrayList<>();

    static class throwToDownloadFiles implements Runnable{
        int numberOfCurrentFile;
        List<String> subListOfURL;
        throwToDownloadFiles(List<String> subListOfURL){
            this.subListOfURL = subListOfURL;
        }
        @Override
        public void run() {
            for(String currentURL : subListOfURL) {
                        numberOfCurrentFile = ++countDownloadingFiles;
                        System.out.println(Thread.currentThread().getName()+ " start download file number " + numberOfCurrentFile);
                        downloadFile(currentURL);
                        System.out.println(Thread.currentThread().getName()+ " finish download file number " + numberOfCurrentFile);

            }
        }
    }

    private static void downloadFile(String path) {
        try {
            File file = new File(path);
            String fileName = file.getName();
            URL url = new URL(path);
            InputStream inputStream = url.openStream();
            Files.copy(inputStream, new File("./ex03/download_files/" + fileName).toPath());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Создание листа со всеми URL
     */
    private static void scanFileOfUrls() {
        try (FileReader fr = new FileReader("./ex03/files_urls.txt"); Scanner scan = new Scanner(fr)) {
            String currentStr;
            while(scan.hasNextLine()){
                currentStr = scan.nextLine();
                currentStr = currentStr.substring(2);
                listOfURLS.add(currentStr);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Создаются под-листы с несколькими URL для каждого из потоков
     */
    private static void createThreads(){
        int fixListOfURLsSize = listOfURLS.size();
        for (int i = 1; i <= threadCounts; ++i) {
            int countURLsInSubList;
            if(i != threadCounts){
                countURLsInSubList = fixListOfURLsSize/threadCounts;
            }else {
                countURLsInSubList = listOfURLS.size();
            }
            List<String> subListOfURL = new ArrayList<>();
            for (int j = 0; j < countURLsInSubList; j++) {
                subListOfURL.add(listOfURLS.get(ANOTHER_URL));
                listOfURLS.remove(ANOTHER_URL);
            }
                Thread downLoading = new Thread(new throwToDownloadFiles(subListOfURL));
                downLoading.start();
        }
    }
        public static void main(String[] args) {
        threadCounts = Integer.parseInt(args[0].substring(15));
        scanFileOfUrls();
        createThreads();
    }
}
