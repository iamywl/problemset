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

    static final long INF = Long.MAX_VALUE / 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            List<List<Edge>> adj = new ArrayList<>(v + 1);
            for (int i = 0; i <= v; i++) adj.add(new ArrayList<>());

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                adj.get(u).add(new Edge(to, w));
            }

            long[] dist = new long[v + 1];
            Arrays.fill(dist, INF);
            dist[s] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(s, 0));

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

            StringBuilder ans = new StringBuilder();
            for (int i = 1; i <= v; i++) {
                if (i > 1) ans.append(" ");
                ans.append(dist[i] == INF ? -1 : dist[i]);
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
