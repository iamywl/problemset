import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static int[][] w;
    static int[][] dp;
    static final int INF = 100000000;

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

    private static int tsp(int mask, int cur) {
        if (mask == (1 << n) - 1) {
            return w[cur][0] > 0 ? w[cur][0] : INF;
        }

        if (dp[mask][cur] != -1) return dp[mask][cur];

        int minCost = INF;
        for (int next = 0; next < n; next++) {
            if ((mask & (1 << next)) == 0 && w[cur][next] > 0) {
                int cost = w[cur][next] + tsp(mask | (1 << next), next);
                if (cost < minCost) {
                    minCost = cost;
                }
            }
        }
        return dp[mask][cur] = minCost;
    }
}
