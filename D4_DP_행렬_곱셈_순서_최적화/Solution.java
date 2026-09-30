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
            long[] p = new long[n + 1];

            for (int i = 1; i <= n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                long r = Long.parseLong(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                if (i == 1) p[0] = r;
                p[i] = c;
            }

            long[][] m = new long[n + 1][n + 1];

            for (int len = 2; len <= n; len++) {
                for (int i = 1; i <= n - len + 1; i++) {
                    int j = i + len - 1;
                    m[i][j] = Long.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        long cost = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                        if (cost < m[i][j]) {
                            m[i][j] = cost;
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(m[1][n]).append("\n");
        }
        System.out.print(sb);
    }
}
