import java.io.*;
import java.util.*;

public class Solution {
    static final int INF = 100000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            int m = Integer.parseInt(br.readLine().trim());

            int[][] dist = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }

            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                if (w < dist[u][v]) {
                    dist[u][v] = w;
                }
            }

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }

            sb.append("#").append(tc).append("\n");
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int val = (dist[i][j] == INF) ? 0 : dist[i][j];
                    sb.append(val).append(j == n ? "" : " ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
