package ex02;

import java.util.ArrayList;
import java.util.List;

public class Program {

    static int resultThreadsSum = 0;
    static List <Thread> threadList = new ArrayList<>();
    private static int arraySize;
    private static int threadCounts;
    private static int[] array;

    /**
     * Вычисление суммы элементов массива
     * @param array непосредственно массив
     * @return сумма
     */
    public static int takeSum(int [] array){
        int result = 0;
        for (int i : array){
            result += i;
        }
        return result;
    }

    public static void fillArray(int [] array){
        for(int i = 0; i < array.length; ++i){
            array[i] = i;
        }
    }

    /**
     * Реализация интерфейса для печати строки в отдельном потоке
     */
   static class calculateSum implements Runnable{

        int indexBegin, indexEnd, result;
        int [] array;

       calculateSum(int indexBegin, int indexEnd, int [] array){
           this.indexBegin = indexBegin;
           this.indexEnd = indexEnd;
           this.array = array;
       }

       @Override
       public void run() {
           result = takeSum(array);
           resultThreadsSum += result;
           System.out.println(Thread.currentThread().getName() + ": from " + indexBegin + " to " + indexEnd + " sum is " + result);
       }
   }

    /**
     * Процедура распределения чисел исходного массива по потокам
     */
   public static void threadCalculating(){
       int counterInArray = 0;
       for (int i = 1; i <= threadCounts; i++) {
           int[] subArray;
           int beginIndexInSubArray = counterInArray;
           if (i != threadCounts) {
               subArray = new int[arraySize / threadCounts];
               for (int j = 0; j < arraySize / threadCounts; j++) {
                   subArray[j] = array[counterInArray];
                   counterInArray++;
               }
           } else {  // Для последнего потока создаётся подмассив со всеми оставшимися элементами
               int lastSubArraySize = arraySize - counterInArray;
               subArray = new int[lastSubArraySize];
               for (int j = 0; j < lastSubArraySize; j++) {
                   subArray[j] = array[counterInArray];
                   counterInArray++;
               }
           }
           Thread threadCalc = new Thread(new calculateSum(beginIndexInSubArray, counterInArray, subArray));
           threadCalc.start();
           threadList.add(threadCalc);
       }
   }

    public static void main(String[] args) {
       arraySize = Integer.parseInt(args[0].substring(12));
       threadCounts = Integer.parseInt(args[1].substring(15));
       array = new int[arraySize];
       fillArray(array);

        /** Расчёт суммы стандартным способом через цикл */
       System.out.println("Sum: " + takeSum(array));

        try {
            threadCalculating();
            /** Ожидание завершения всех потоков */
            for (Thread current : threadList) {
                current.join();
            }
        } catch (InterruptedException e){
            System.err.println(e.getMessage());
        }
        System.out.println("Sum by threads: " + resultThreadsSum);
    }
}
