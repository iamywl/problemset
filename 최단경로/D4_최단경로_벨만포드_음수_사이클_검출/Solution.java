import java.io.*;
import java.util.*;

public class Solution {
    static class Edge {
        int u, v, w;
        Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
    }

    static final long INF = 1000000000000L;

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

            Edge[] edges = new Edge[m];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(u, v, w);
            }

            long[] dist = new long[n + 1];
            Arrays.fill(dist, INF);
            dist[1] = 0;

            boolean hasNegativeCycle = false;

            for (int round = 1; round <= n; round++) {
                for (Edge e : edges) {
                    if (dist[e.u] != INF && dist[e.u] + e.w < dist[e.v]) {
                        dist[e.v] = dist[e.u] + e.w;
                        if (round == n) {
                            hasNegativeCycle = true;
                        }
                    }
                }
            }

            sb.append("#").append(tc).append("\n");
            if (hasNegativeCycle) {
                sb.append("-1\n");
            } else {
                for (int i = 2; i <= n; i++) {
                    sb.append(dist[i] == INF ? -1 : dist[i]).append("\n");
                }
            }
        }
        System.out.print(sb);
    }
}
