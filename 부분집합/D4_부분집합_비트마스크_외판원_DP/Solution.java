import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] W;
    static int[][] dp;
    static final int INF = 100000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            W = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) W[i][j] = Integer.parseInt(st.nextToken());
            }

            dp = new int[N][1 << N];
            for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

            int ans = tsp(0, 1);

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    static int tsp(int city, int mask) {
        if (mask == (1 << N) - 1) {
            return W[city][0] > 0 ? W[city][0] : INF;
        }

        if (dp[city][mask] != -1) return dp[city][mask];

        int minCost = INF;
        for (int next = 0; next < N; next++) {
            if ((mask & (1 << next)) == 0 && W[city][next] > 0) {
                int cost = W[city][next] + tsp(next, mask | (1 << next));
                if (cost < minCost) minCost = cost;
            }
        }

        return dp[city][mask] = minCost;
    }
}
