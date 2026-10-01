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
            int k = Integer.parseInt(br.readLine().trim());
            // dp[t][color]: 0: R, 1: G, 2: B
            long[][] dp = new long[k + 1][3];
            dp[1][0] = 1;
            dp[1][1] = 1;
            dp[1][2] = 1;

            for (int t = 2; t <= k; t++) {
                dp[t][0] = dp[t - 1][1] + dp[t - 1][2];
                dp[t][1] = dp[t - 1][0] + dp[t - 1][2];
                dp[t][2] = dp[t - 1][0] + dp[t - 1][1];
            }

            long ans = dp[k][1] + dp[k][2]; // R(0) 제외
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
