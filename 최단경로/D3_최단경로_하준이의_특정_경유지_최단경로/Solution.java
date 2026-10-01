import java.io.*;
import java.util.*;

public class Solution {
    static class Edge implements Comparable<Edge> {
        int to, weight;
        Edge(int to, int weight) { this.to = to; this.weight = weight; }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static final int INF = 1000000000;
    static int n;
    static List<Edge>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                adj[u].add(new Edge(to, w));
            }

            int[] dist1 = dijkstra(1);
            int[] distV = dijkstra(v);

            int ans = -1;
            if (dist1[v] != INF && distV[n] != INF) {
                ans = dist1[v] + distV[n];
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.weight > dist[cur.to]) continue;

            for (Edge nxt : adj[cur.to]) {
                if (dist[cur.to] + nxt.weight < dist[nxt.to]) {
                    dist[nxt.to] = dist[cur.to] + nxt.weight;
                    pq.offer(new Edge(nxt.to, dist[nxt.to]));
                }
            }
        }
        return dist;
    }
}
