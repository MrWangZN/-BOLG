package author.wzn.algorithms.sort;

import java.util.Arrays;

/*
* @Author: 王振南
* @Date: 2021/8/9
* @Description: 插入排序，插入排序的思想是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
*/
public class InsertionSort {

    public static void main(String[] args) {
//        int[] array = {1,2,3,4,5,6};
        int[] array = {34 ,25, 56, 57 ,77 ,39 ,36, 63 ,83, 46};
        insertionSortBetter(array);
        System.out.println(Arrays.toString(array));
    }

    //普通的插入排序
    public static void insertionSort(int[] array) {

        int insertVal;
        int insertIndex;
        for (int i = 1; i < array.length; i++) {
            insertIndex = i -1;
            insertVal = array[i];
            while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            array[++insertIndex] = insertVal;
        }
    }
    //优化的插入排序
    public static void insertionSortBetter(int[] array) {
        int insertVal;
        int insertIndex;
        for (int i = 1; i < array.length; i++) {
            insertIndex = i -1;
            insertVal = array[i];
            while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            //通过对下标的判断是否需要交换位置
            if(i  -1 > insertIndex) array[++insertIndex] = insertVal;
        }
    }
}
