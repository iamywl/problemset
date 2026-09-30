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
        long t;
        Edge(int to, long t) {
            this.to = to;
            this.t = t;
        }
    }

    static final long INF = Long.MAX_VALUE / 2;

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
            long k = Long.parseLong(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            List<List<Edge>> adj = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                long t = Long.parseLong(st.nextToken());
                adj.get(u).add(new Edge(v, t));
            }

            long[] dist = new long[n + 1];
            Arrays.fill(dist, INF);
            dist[s] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(s, 0));

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if (cur.dist > dist[cur.v]) continue;
                if (cur.v == dest) break;

                for (Edge edge : adj.get(cur.v)) {
                    long addedCost = edge.t + (edge.to == dest ? 0 : k);
                    if (dist[cur.v] + addedCost < dist[edge.to]) {
                        dist[edge.to] = dist[cur.v] + addedCost;
                        pq.offer(new Node(edge.to, dist[edge.to]));
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dist[dest] == INF ? -1 : dist[dest]).append("\n");
        }
        System.out.print(sb);
    }
}
