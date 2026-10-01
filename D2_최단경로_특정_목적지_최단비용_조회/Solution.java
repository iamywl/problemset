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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            int m = Integer.parseInt(br.readLine().trim());

            List<Edge>[] adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                adj[u].add(new Edge(v, w));
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int[] dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            PriorityQueue<Edge> pq = new PriorityQueue<>();

            dist[start] = 0;
            pq.offer(new Edge(start, 0));

            while (!pq.isEmpty()) {
                Edge cur = pq.poll();
                if (cur.to == end) break;
                if (cur.weight > dist[cur.to]) continue;

                for (Edge nxt : adj[cur.to]) {
                    if (dist[cur.to] + nxt.weight < dist[nxt.to]) {
                        dist[nxt.to] = dist[cur.to] + nxt.weight;
                        pq.offer(new Edge(nxt.to, dist[nxt.to]));
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dist[end]).append("\n");
        }
        System.out.print(sb);
    }
}
