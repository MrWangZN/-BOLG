package author.wzn.algorithms.practice.leetCode;

import java.util.Arrays;

public class LeetCode_787 {
    public static void main(String[] args) {
        int[][] flights = {{0,1,100},{1,2,100},{0,2,500},{0,3,50},{3,2,50}};
        System.out.println(findCheapestPrice(4, flights, 3, 2, 1));
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int INF = 10000 * 101 + 1;
        int[][] f = new int[k + 2][n];
        for (int i = 0; i < k + 2; ++i) {
            Arrays.fill(f[i], INF);
        }
        f[0][src] = 0;
        for (int t = 1; t <= k + 1; ++t) {
            for (int[] flight : flights) {
                int j = flight[0], i = flight[1], cost = flight[2];
                //与上一趟航班是到j+cost的对比,看看谁的价格更低
                f[t][i] = Math.min(f[t][i], f[t - 1][j] + cost);
            }
        }
        int ans = INF;
        for (int t = 1; t <= k + 1; ++t) {
            ans = Math.min(ans, f[t][dst]);
        }
        return ans == INF ? -1 : ans;
    }
}
