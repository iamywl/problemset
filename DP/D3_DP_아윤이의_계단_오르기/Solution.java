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
            int[] score = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                score[i] = Integer.parseInt(br.readLine().trim());
            }

            if (n == 1) {
                sb.append("#").append(tc).append(" ").append(score[1]).append("\n");
                continue;
            } else if (n == 2) {
                sb.append("#").append(tc).append(" ").append(score[1] + score[2]).append("\n");
                continue;
            }

            int[] dp = new int[n + 1];
            dp[1] = score[1];
            dp[2] = score[1] + score[2];
            dp[3] = Math.max(score[1] + score[3], score[2] + score[3]);

            for (int i = 4; i <= n; i++) {
                dp[i] = Math.max(dp[i - 2], dp[i - 3] + score[i - 1]) + score[i];
            }

            sb.append("#").append(tc).append(" ").append(dp[n]).append("\n");
        }
        System.out.print(sb);
    }
}
