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

        int maxN = 100000;
        int[] dp = new int[maxN + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= maxN; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            sb.append("#").append(tc).append(" ").append(dp[n]).append("\n");
        }
        System.out.print(sb);
    }
}
