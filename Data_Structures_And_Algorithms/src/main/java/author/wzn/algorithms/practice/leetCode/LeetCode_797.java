package author.wzn.algorithms.practice.leetCode;


//797. 所有可能的路径

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_797 {
    public static void main(String[] args) {

    }

    static List list = new ArrayList<List<Integer>>();
    static LinkedList<Integer> temp = new LinkedList<Integer>();
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        temp.add(0);
        get(graph,0,temp);
        return list;
    }
    public static void get(int[][] graph,int n,LinkedList<Integer> temp){
        if(n==graph.length-1){
            list.add(new ArrayList(temp));
            return;
        }
        for(int i : graph[n]){
            temp.add(i);
            get(graph,i,temp);
            temp.removeLast();
        }
    }
}
