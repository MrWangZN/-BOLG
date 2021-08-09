package author.wzn.algorithms.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = {75 ,45, 17 ,92 ,25 ,52 ,38 ,51 ,32 ,28};
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array, int left, int right) {

        if (left < right) {

            int l = left;
            int r = right;
            int pivot = array[left];
            while (l < r) {

                while (l < r && pivot <= array[r]) { //要带上等号否则死循环 两边都相等就不会进入r-- 或者 l++就不会退出循环
                    r--;                             //两个都带上等号就好了，只要一个等号也是可以的
                }
                array[l] = array[r];
                while (l < r && pivot >= array[l]) {
                    l++;
                }
                array[r] = array[l];
            }
            array[l] = pivot;
            quickSort(array, left, l - 1);
            quickSort(array, l+1, right);
        }



    }

}
