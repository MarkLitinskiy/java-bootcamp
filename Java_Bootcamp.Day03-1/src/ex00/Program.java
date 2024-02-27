package ex00;

public class Program {
    /**
     * Реализация интерфейса для печати строки в отдельном потоке
     */
    public static class printThread implements Runnable{
        String str;
        int count;
        printThread(String str, int count){
            this.str = str;
            this.count = count;
        }

        public static void printStr(String str, int count){
            for (int i = 0; i < count; ++i) {
                System.out.println(str);
            }
        }

        @Override
        public void run() {
            printStr(str, count);
        }
    }

    public static void main(String[] args) {
        /** Парсинг количества вывода из входного параметра --count=50 */
        int count = Integer.parseInt(args[0].substring(8));

        Thread eggThread = new Thread(new printThread("Egg", count));
        Thread henThread = new Thread(new printThread("Hen", count));

        /** Старт двух печатающих потоков, а также печать в основном потоке main */
        eggThread.start();
        henThread.start();
        printThread.printStr("Human", count);
    }
}
