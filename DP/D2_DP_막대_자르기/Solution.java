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
            int[] p = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                p[i] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                int maxVal = 0;
                for (int i = 1; i <= j; i++) {
                    maxVal = Math.max(maxVal, p[i] + dp[j - i]);
                }
                dp[j] = maxVal;
            }

            sb.append("#").append(tc).append(" ").append(dp[n]).append("\n");
        }
        System.out.print(sb);
    }
}
