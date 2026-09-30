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
            int[][] tri = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j <= i; j++) {
                    tri[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[n][n];
            dp[0][0] = tri[0][0];

            for (int i = 1; i < n; i++) {
                dp[i][0] = dp[i - 1][0] + tri[i][0];
                for (int j = 1; j < i; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + tri[i][j];
                }
                dp[i][i] = dp[i - 1][i - 1] + tri[i][i];
            }

            int maxAns = 0;
            for (int j = 0; j < n; j++) {
                if (dp[n - 1][j] > maxAns) maxAns = dp[n - 1][j];
            }

            sb.append("#").append(tc).append(" ").append(maxAns).append("\n");
        }
        System.out.print(sb);
    }
}
