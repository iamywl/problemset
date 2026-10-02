import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    static class Edge implements Comparable<Edge> {
        int to, cost;
        Edge(int to, int cost) { this.to = to; this.cost = cost; }
        public int compareTo(Edge o) { return Integer.compare(this.cost, o.cost); }
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

            ArrayList<Edge>[] adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                adj[u].add(new Edge(v, c));
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int[] dist = new int[n + 1];
            int[] parent = new int[n + 1];
            int INF = 1000000000;
            Arrays.fill(dist, INF);

            PriorityQueue<Edge> pq = new PriorityQueue<>();
            dist[start] = 0;
            pq.offer(new Edge(start, 0));

            while (!pq.isEmpty()) {
                Edge cur = pq.poll();
                if (cur.cost > dist[cur.to]) continue;
                if (cur.to == end) break;

                for (Edge next : adj[cur.to]) {
                    if (dist[next.to] > dist[cur.to] + next.cost) {
                        dist[next.to] = dist[cur.to] + next.cost;
                        parent[next.to] = cur.to;
                        pq.offer(new Edge(next.to, dist[next.to]));
                    }
                }
            }

            ArrayList<Integer> path = new ArrayList<>();
            for (int at = end; at != 0; at = parent[at]) {
                path.add(at);
            }
            Collections.reverse(path);

            sb.append("#").append(tc).append(" ").append(dist[end]).append("\n");
            sb.append(path.size()).append("\n");
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i)).append(i == path.size() - 1 ? "" : " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
