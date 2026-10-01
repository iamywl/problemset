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
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] coins = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[k + 1];
            dp[0] = 1;

            for (int coin : coins) {
                for (int j = coin; j <= k; j++) {
                    dp[j] += dp[j - coin];
                }
            }

            sb.append("#").append(tc).append(" ").append(dp[k]).append("\n");
        }
        System.out.print(sb);
    }
}
