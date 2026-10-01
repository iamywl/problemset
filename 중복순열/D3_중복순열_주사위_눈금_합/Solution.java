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
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            long[][] dp = new long[k + 1][s + 1];
            dp[0][0] = 1;

            for (int i = 1; i <= k; i++) {
                for (int cur = 0; cur <= s; cur++) {
                    if (dp[i - 1][cur] == 0) continue;
                    for (int roll = 1; roll <= m; roll++) {
                        if (cur + roll <= s) {
                            dp[i][cur + roll] += dp[i - 1][cur];
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dp[k][s]).append("\n");
        }
        System.out.print(sb);
    }
}
