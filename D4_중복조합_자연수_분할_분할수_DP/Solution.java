import java.io.*;
import java.util.*;

public class Solution {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        int maxN = 1000;
        int[] dp = new int[maxN + 1];
        dp[0] = 1;
        for (int i = 1; i <= maxN; i++) {
            for (int j = i; j <= maxN; j++) {
                dp[j] = (dp[j] + dp[j - i]) % MOD;
            }
        }

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            sb.append("#").append(tc).append(" ").append(dp[n]).append("\n");
        }
        System.out.print(sb);
    }
}
