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

        int maxVal = 2500;
        int[][] c = new int[maxVal + 1][maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % MOD;
            }
        }

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            long ans = 0;
            int totalMask = 1 << n;

            for (int mask = 0; mask < totalMask; mask++) {
                int k = Integer.bitCount(mask);
                int rem = s - k * (m + 1);

                if (rem >= 0) {
                    long ways = c[n + rem - 1][rem];
                    if (k % 2 == 0) {
                        ans = (ans + ways) % MOD;
                    } else {
                        ans = (ans - ways + MOD) % MOD;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
