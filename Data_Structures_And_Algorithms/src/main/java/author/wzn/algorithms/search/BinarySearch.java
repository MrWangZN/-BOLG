package author.wzn.algorithms.search;


/*
* @Author: 王振南
* @Date: 2021/8/9
* @Description: 二分查找（折半查找）算法
*/
public class BinarySearch {

    public static void main(String[] args) {
        int[] array = {7, 9, 30, 30, 31, 43, 60, 88, 92, 94};
        System.out.println("binarySearchRecursion = " + binarySearchRecursion(array, 1,0,array.length-1));
    }

    //使用循环的方式解决
    public static int binarySearch(int[] array, int target) {

        int mid;
        int l = 0;
        int r = array.length;
        while (l<=r) {

            mid = (l + r) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return -1;
    }


    //使用递归的方式实现二分查找
    public static int binarySearchRecursion(int[] array, int target, int left, int right) {

        if (left <= right) {
            int mid = left + (right - left) / 2;
            if(array[mid] == target) return mid;
            else if(array[mid] < target) return binarySearchRecursion(array, target, mid + 1, right);
            else return binarySearchRecursion(array, target, left, mid -1);
        }
        return -1;
    }
}
