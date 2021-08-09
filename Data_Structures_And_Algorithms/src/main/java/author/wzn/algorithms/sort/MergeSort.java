package author.wzn.algorithms.sort;

import java.util.Arrays;


/*
* @Author: 王振南
* @Date: 2021/8/9
* @Description: 归并排序，分而治之，将数组切切分成一个一个，然后合并两个有序的虚假的数组（还是原来那个数组,只是用指针切分了)
*               有序的原因是，最开始的时候使用一个数字和另外一个数字合并不涉及有序，他们合并成有序，以此继续合并一直都是有序的合并。
*/
public class MergeSort {

    public static void main(String[] args) {
        int[] array = {20 ,22, 18, 30, 35 ,11 ,60, 59 ,43, 95,22,20,30};
        mergeSort(array,0,array.length-1,new int[array.length]);
        System.out.println(Arrays.toString(array));
    }


    public static void mergeSort(int[] array, int left, int right, int[] temp) {

        if (left < right) {

            int mid = left + (right - left) / 2;
            //像树一样拆分结构
            mergeSort(array, left, mid, temp);          //左拆分
            mergeSort(array, mid+1, right, temp);  //右拆分
            merge(array, left, mid, right, temp);      //合并

        }

    }

    private static void merge(int[] array, int left, int mid, int right, int[] temp) {

        if (left < right) {

            int l = left;
            int r = mid + 1;
            int tempIndex = 0;
            while (l <= mid && r <= right) {

                if (array[l] < array[r]) {
                    temp[tempIndex] = array[l++];
                }else{
                    temp[tempIndex] = array[r++];
                }
                tempIndex++;
            }

            while (l <= mid) {
                temp[tempIndex] = array[l++];
                tempIndex++;
            }
            while (r <= right) {
                temp[tempIndex] = array[r++];
                tempIndex++;
            }

            //放回到原数组中
            int leftIndex = left;
            tempIndex = 0;
            while (leftIndex <= right) {
                array[leftIndex++] = temp[tempIndex++];

            }
        }
    }

}
