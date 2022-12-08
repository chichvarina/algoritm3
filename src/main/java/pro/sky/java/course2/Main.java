package pro.sky.java.course2;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Все проверки теперь в тестах");

        Integer[] array1 = generateArray();
        Integer[] array2 = Arrays.copyOf(array1, array1.length);
        Integer[] array3 = Arrays.copyOf(array1, array1.length);

        Long time0 = System.currentTimeMillis();
        sortBubble(array1);
        Long time1 = System.currentTimeMillis();
        long durationBubble = time1-time0;
        System.out.println("Сортировка bubble = "+durationBubble);

        Long time2 = System.currentTimeMillis();
        sortSelection(array2);
        Long time3 = System.currentTimeMillis();
        long durationSelection = time3-time2;
        System.out.println("Сортировка Selection = "+durationSelection);

        Long time4 = System.currentTimeMillis();
        sortInsertion(array3);
        Long time5 = System.currentTimeMillis();
        long durationInsertion = time5-time4;
        System.out.println("Сортировка Insertion = "+durationInsertion);

        //результат
        //Сортировка bubble = 5415
        //Сортировка Selection = 626
        //Сортировка Insertion = 1624
        //сортировка selection - самая быстрая

    }

    private static Integer[] generateArray(){
        Random random = new Random(System.currentTimeMillis());
        Integer[] array = new Integer[30000];
        for (int i = 0; i < array.length; i++) {
            array[i]= random.nextInt(array.length);
        }
        return array;
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}