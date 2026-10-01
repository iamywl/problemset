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

            int[] dp = new int[k + 1];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int w = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                for (int cap = k; cap >= w; cap--) {
                    if (dp[cap - w] + v > dp[cap]) {
                        dp[cap] = dp[cap - w] + v;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dp[k]).append("\n");
        }
        System.out.print(sb);
    }
}
