package author.wzn.algorithms.sort;

import java.util.Arrays;

/*
* @Author: 王振南
* @Date: 2021/8/9
* @Description: 选择排序思想：未排序序列中找到最小（大）元素，存放到排序序列的末尾位置
*/
public class SelectionSort {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6};
//        int[] array = {40, 82, 49,5 ,42 ,83 ,58 ,4, 32, 56};
        selectionSortBetter(array);
        System.out.println(Arrays.toString(array));
    }

    //普通的选择排序
    public static void selectionSort(int[] array) {
        int min;
        int temp;
        int minIndex;
        for (int i = 0; i < array.length -1; i++) {
            min = array[i];
            minIndex = i;//默认自己是最小的
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    minIndex = j;
                    min = array[j];
                }
            }
            temp = array[i];
            array[i] = min;
            array[minIndex] = temp;
        }
    }
    //优化的选择排序
    public static void selectionSortBetter(int[] array) {
        int min;
        int temp;
        int minIndex;
        for (int i = 0; i < array.length -1; i++) {
            min = array[i];
            minIndex = i;//默认自己是最小的
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    minIndex = j;
                    min = array[j];
                }
            }
            if (i < minIndex) {
                temp = array[i];
                array[i] = min;
                array[minIndex] = temp;
            }
        }
    }
}
