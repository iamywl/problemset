import java.io.*;
import java.util.*;

public class Solution {
    static class Node implements Comparable<Node> {
        int v;
        long dist;
        Node(int v, long dist) {
            this.v = v;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    static class Edge {
        int to;
        long w;
        Edge(int to, long w) {
            this.to = to;
            this.w = w;
        }
    }

    static final long INF = Long.MAX_VALUE / 4;
    static int n;
    static List<List<Edge>> adj;

    static long[] dijkstra(int start) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.dist > dist[cur.v]) continue;

            for (Edge edge : adj.get(cur.v)) {
                if (dist[cur.v] + edge.w < dist[edge.to]) {
                    dist[edge.to] = dist[cur.v] + edge.w;
                    pq.offer(new Node(edge.to, dist[edge.to]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            adj = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                adj.get(u).add(new Edge(v, w));
                adj.get(v).add(new Edge(u, w));
            }

            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            long[] distFrom1 = dijkstra(1);
            long[] distFromV1 = dijkstra(v1);
            long[] distFromV2 = dijkstra(v2);

            long path1 = distFrom1[v1] + distFromV1[v2] + distFromV2[n];
            long path2 = distFrom1[v2] + distFromV2[v1] + distFromV1[n];

            long ans = Math.min(path1, path2);
            if (ans >= INF) {
                sb.append("#").append(tc).append(" -1\n");
            } else {
                sb.append("#").append(tc).append(" ").append(ans).append("\n");
            }
        }
        System.out.print(sb);
    }
}
