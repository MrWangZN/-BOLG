package author.wzn.algorithms.sort;

import java.util.Arrays;

/*
* @Author: 王振南
* @Date: 2021/8/9
* @Description: 希尔排序：也称递减增量排序算法，是插入排序的一种更高效的改进版本。(对插入排序的升级)
*/
public class ShellSort {

    public static void main(String[] args) {
        int[] array = {420 ,22, 18, 30, 35 ,11 ,60, 59 ,43, 95,22,20,30};
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }
    //希尔排序
    public static void shellSort(int[] array) {
        int insertVal;
        int insertIndex;
        for (int i = array.length / 2; i > 0; i /= 2) {
            for (int j = i; j < array.length; j++) {
                insertVal = array[j];
                insertIndex = j - i;
                while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                    array[insertIndex + i] = array[insertIndex];
                    insertIndex-=i;
                }
                array[insertIndex + i] = insertVal;
            }
        }
    }

}
