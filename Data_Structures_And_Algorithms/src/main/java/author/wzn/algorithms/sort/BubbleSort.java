package author.wzn.algorithms.sort;

import java.util.Arrays;

/*
* @Author: 王振南
* @Date: 2021/8/9
* @Description: 排序算法-冒泡排序
*/
public class BubbleSort {

    public static void main(String[] args) {
//        int[] array = {1,2,3,4,5,6};
        int[] array = {94, 31, 60, 92, 7, 88, 9, 30, 30, 43};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    //两个方法都是用count标记最外层循环次数

    //基础版本冒泡排序
    public static void bubbleSort(int[] array) {
        int temp;
        int count = 0;
        for (int i = 1; i < array.length; i++) {
            count++;
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j+1] = temp;
                }
            }
        }
        System.out.println(count);
    }
    //优化版本冒泡排序  排序好后就直接推出（一次循环中没有交换位置）
    public static void bubbleSortBetter(int[] array) {

        int temp;
        boolean flag = false;
        int count = 0;
        for (int i = 1; i < array.length; i++) {
            count++;
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j+1] = temp;
                    flag =true;
                }
            }
            if(!flag) {
                System.out.println(count);
                return;}
            flag = false;
        }
    }

}
