import java.io.*;
import java.util.*;

public class Solution {
    static class Edge {
        int u, v;
        long w;
        Edge(int u, int v, long w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static final long INF = Long.MAX_VALUE / 4;

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
                long w = Long.parseLong(st.nextToken());
                edges[i] = new Edge(u, v, w);
            }

            long[] dist = new long[n + 1];
            Arrays.fill(dist, INF);
            dist[1] = 0;

            boolean hasNegativeCycle = false;

            for (int i = 1; i <= n; i++) {
                for (Edge edge : edges) {
                    if (dist[edge.u] != INF && dist[edge.u] + edge.w < dist[edge.v]) {
                        dist[edge.v] = dist[edge.u] + edge.w;
                        if (i == n) {
                            hasNegativeCycle = true;
                        }
                    }
                }
            }

            sb.append("#").append(tc).append("\n");
            if (hasNegativeCycle) {
                sb.append("-1\n");
            } else {
                StringBuilder ans = new StringBuilder();
                for (int i = 2; i <= n; i++) {
                    if (i > 2) ans.append(" ");
                    ans.append(dist[i] == INF ? -1 : dist[i]);
                }
                sb.append(ans).append("\n");
            }
        }
        System.out.print(sb);
    }
}
