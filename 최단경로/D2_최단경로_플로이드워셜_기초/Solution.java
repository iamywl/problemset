import java.io.*;
import java.util.*;

public class Solution {
    static final long INF = 100000000000L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            long[][] d = new long[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                Arrays.fill(d[i], INF);
                d[i][i] = 0;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                if (w < d[u][v]) d[u][v] = w;
            }

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    if (d[i][k] == INF) continue;
                    for (int j = 1; j <= n; j++) {
                        if (d[k][j] == INF) continue;
                        if (d[i][k] + d[k][j] < d[i][j]) {
                            d[i][j] = d[i][k] + d[k][j];
                        }
                    }
                }
            }

            sb.append("#").append(tc).append("\n");
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (j > 1) sb.append(" ");
                    sb.append(d[i][j] == INF ? -1 : d[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
