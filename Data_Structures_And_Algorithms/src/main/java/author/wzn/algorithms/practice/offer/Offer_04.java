package author.wzn.algorithms.practice.offer;

////箭指Offer 04 二维数组中的查找
public class Offer_04
{
   public static void main(String[] args){
	
   }
   public static boolean findNumberIn2DArray(int[][] matrix, int target){
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int two = matrix[0].length;
        int one = matrix.length;
        int twoIndex = two - 1;
        int oneIndex = 0;
        while(twoIndex >=0 && oneIndex < one){
            if(target > matrix[oneIndex][twoIndex]){
                oneIndex++;
            }else if(target == matrix[oneIndex][twoIndex]){
                return true;
            }else{
                twoIndex--;
                while(twoIndex >=0 && oneIndex < one){
                    if(matrix[oneIndex][twoIndex] > target){
                        twoIndex--;
                    }else if(target == matrix[oneIndex][twoIndex]){
                        return true;
                    }else{
                        oneIndex++;
                    }
                }
            }
        }
        return false;
   }

   public static boolean findNumberIn2DArray_standard(int[][] matrix, int target){
		
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int two = matrix[0].length;
        int one = matrix.length;
        int twoIndex = two - 1;
        int oneIndex = 0;
		while(oneIndex < one && twoIndex >= 0){
			if(target == matrix[oneIndex][twoIndex]){
				return true;
			}
			if(target > matrix[oneIndex][twoIndex]){
				oneIndex++;
			}else{
				twoIndex--;
			}
		}
		return false;
   }
}
