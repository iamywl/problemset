import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static int[][] w;
    static int[][] dp;
    static final int INF = 1000000000;

    static int tsp(int mask, int u) {
        if (mask == (1 << n) - 1) {
            return w[u][0] > 0 ? w[u][0] : INF;
        }

        if (dp[mask][u] != -1) return dp[mask][u];

        int minCost = INF;
        for (int v = 0; v < n; v++) {
            if ((mask & (1 << v)) == 0 && w[u][v] > 0) {
                int cost = tsp(mask | (1 << v), v) + w[u][v];
                if (cost < minCost) {
                    minCost = cost;
                }
            }
        }

        return dp[mask][u] = minCost;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            w = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    w[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp = new int[1 << n][n];
            for (int i = 0; i < (1 << n); i++) {
                Arrays.fill(dp[i], -1);
            }

            int ans = tsp(1, 0);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
