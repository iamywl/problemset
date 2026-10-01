import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            int[][] cost = new int[n][3];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                cost[i][0] = Integer.parseInt(st.nextToken());
                cost[i][1] = Integer.parseInt(st.nextToken());
                cost[i][2] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[n][3];
            dp[0][0] = cost[0][0];
            dp[0][1] = cost[0][1];
            dp[0][2] = cost[0][2];

            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
            }

            int ans = Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
