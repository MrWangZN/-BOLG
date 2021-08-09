package author.wzn.algorithms.sort;

import java.util.Arrays;

/*
* @Author: 王振南
* @Date: 2021/8/9
* @Description: 堆排序：堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于
*               （或者大于）它的父节点。堆排序可以说是一种利用堆的概念来排序的选择排序。这里设计成大顶堆
*/
public class Heapsort {

    public static void main(String[] args) {
        int[] array = {40, 82, 49,5 ,42 ,83 ,58 ,4, 32, 56};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void heapSort(int[] array) {

        //将array转换成一个大顶堆
        for (int i = array.length/2-1; i >= 0; i--) {
            heap(array, i, array.length);
        }

        int temp = 0;
        for (int i = array.length - 1; i > 0 ; i--) {
            temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heap(array, 0, i);
        }
    }

    private static void heap(int[] array, int index, int length) {

        int temp = array[index];
        for (int i = 2 * index + 1; i < length; i = i * 2 + 1) {
            if (i + 1 < length && array[i] < array[i + 1]) {
                i++;
            }
            if (temp < array[i]) {
                array[index] = array[i];
                index = i;
            }
        }
        array[index] = temp;
    }
}
