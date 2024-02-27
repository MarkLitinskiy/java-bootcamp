package ex01;

public class Program {
    /**
     * Реализация интерфейса для печати строки в отдельном потоке
     */
    static class printThread implements Runnable {
        String str;
        int count;

        printThread(String str, int count){
            this.str = str;
            this.count = count;
        }

        public static void printStr(String str, int count) throws InterruptedException {
            for (int i = 0; i < count; ++i) {
                System.out.println(str);
                Thread.sleep(100);
            }
        }
        @Override
        public void run() {
            try {
                printStr(str, count);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        /** Парсинг количества вывода из входного параметра --count=50 */
        int count = Integer.parseInt(args[0].substring(8));
        Thread eggThread = new Thread(new printThread("Egg", count));
        Thread henThread = new Thread(new printThread("Hen", count));

        /** Старт двух печатающих потоков */
        eggThread.start();
        henThread.start();
    }
}
