package author.wzn.algorithms.sort;

import java.util.Arrays;
import java.util.OptionalInt;

public class RadixSort {

    public static void main(String[] args) {
        int[] array = {94 ,31 ,60 ,92, 7 ,88, 9 ,30 ,30, 43};
        radixSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void radixSort(int[] array) {

        int max = getMax(array);

        int length = (max + "").length();

        int[][] bucket = new int[10][array.length];
        int[] count = new int[10];
        for (int i = 0, n = 1; i < length; i++, n *= 10) {

            for (int j = 0; j < array.length; j++) {

                int dig = array[j] / n % 10;
                bucket[dig][count[dig]] = array[j];
                count[dig]++;
            }
            int index = 0;
            for (int j = 0; j < bucket.length; j++) {  //直接写成10也可以

                if (count[j] > 0) {

                    for (int x = 0; x < count[j]; x++) {
                        array[index++] = bucket[j][x];
                    }
                    count[j] = 0;
                }
            }
        }
    }

    private static int getMax(int[] array) {
        return Arrays.stream(array).max().getAsInt();
    }

}
