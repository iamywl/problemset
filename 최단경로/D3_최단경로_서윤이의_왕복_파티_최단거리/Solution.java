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
            int x = Integer.parseInt(st.nextToken());

            List<Edge>[] forwardAdj = new ArrayList[n + 1];
            List<Edge>[] reverseAdj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                forwardAdj[i] = new ArrayList<>();
                reverseAdj[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                forwardAdj[u].add(new Edge(to, t));
                reverseAdj[to].add(new Edge(u, t));
            }

            int[] distGo = dijkstra(x, reverseAdj);  // i -> x
            int[] distBack = dijkstra(x, forwardAdj); // x -> i

            int maxRoundTrip = 0;
            for (int i = 1; i <= n; i++) {
                if (distGo[i] != INF && distBack[i] != INF) {
                    int total = distGo[i] + distBack[i];
                    if (total > maxRoundTrip) maxRoundTrip = total;
                }
            }

            sb.append("#").append(tc).append(" ").append(maxRoundTrip).append("\n");
        }
        System.out.print(sb);
    }

    private static int[] dijkstra(int start, List<Edge>[] adj) {
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
