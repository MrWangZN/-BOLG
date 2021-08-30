package learner.wzn.gc;

import java.util.*;

//leetcode 题目 晚点整理过去
public class Test {
    static List list = new ArrayList<List<Integer>>();
    static Deque temp = new ArrayDeque();



    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        temp.offerLast(0);
        get(graph, 0, temp);
        return list;
    }

    public static void get(int[][] graph, int n, Deque<Integer> tempp) {
        if (n == graph.length - 1) {
            list.add(new ArrayList<Integer>(tempp));
            return;
        }
        for (int i : graph[n]) {
            tempp.offerLast(i);
            get(graph, i, temp);
            tempp.pollLast();
        }
    }
    public static void main(String[] args) {
        //也可以改用LinkedList();
        System.out.println(allPathsSourceTarget(new int[][]{{4, 3, 1}, {3, 2, 4}, {3}, {4}, {0}}));
    }
}
