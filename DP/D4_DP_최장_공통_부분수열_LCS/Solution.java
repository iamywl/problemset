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
            String a = br.readLine().trim();
            String b = br.readLine().trim();

            int n = a.length();
            int m = b.length();
            int[][] dp = new int[n + 1][m + 1];

            for (int i = 1; i <= n; i++) {
                char ca = a.charAt(i - 1);
                for (int j = 1; j <= m; j++) {
                    char cb = b.charAt(j - 1);
                    if (ca == cb) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            int len = dp[n][m];
            if (len == 0) {
                sb.append("#").append(tc).append(" 0 0\n");
                continue;
            }

            // 역추적
            char[] res = new char[len];
            int rIdx = len - 1;
            int i = n, j = m;
            while (i > 0 && j > 0) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    res[rIdx--] = a.charAt(i - 1);
                    i--;
                    j--;
                } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }

            sb.append("#").append(tc).append(" ").append(len).append(" ").append(new String(res)).append("\n");
        }
        System.out.print(sb);
    }
}
