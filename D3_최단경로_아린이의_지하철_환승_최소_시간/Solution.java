import java.io.*;
import java.util.*;

public class Solution {
    static class Edge {
        int to, time, line;
        Edge(int to, int time, int line) { this.to = to; this.time = time; this.line = line; }
    }

    static class State implements Comparable<State> {
        int node, line, time;
        State(int node, int line, int time) { this.node = node; this.line = line; this.time = time; }
        @Override
        public int compareTo(State o) {
            return Integer.compare(this.time, o.time);
        }
    }

    static final int INF = 1000000000;

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
            int h = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            List<Edge>[] adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                adj[u].add(new Edge(v, t, l));
                adj[v].add(new Edge(u, t, l));
            }

            int[][] dist = new int[n + 1][11]; // line 0: initial
            for (int i = 1; i <= n; i++) Arrays.fill(dist[i], INF);

            PriorityQueue<State> pq = new PriorityQueue<>();
            dist[start][0] = 0;
            pq.offer(new State(start, 0, 0));

            int ans = INF;

            while (!pq.isEmpty()) {
                State cur = pq.poll();
                if (cur.node == end) {
                    ans = cur.time;
                    break;
                }
                if (cur.time > dist[cur.node][cur.line]) continue;

                for (Edge nxt : adj[cur.node]) {
                    int extra = (cur.line != 0 && cur.line != nxt.line) ? h : 0;
                    int nxtTime = cur.time + nxt.time + extra;

                    if (nxtTime < dist[nxt.to][nxt.line]) {
                        dist[nxt.to][nxt.line] = nxtTime;
                        pq.offer(new State(nxt.to, nxt.line, nxtTime));
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(ans == INF ? -1 : ans).append("\n");
        }
        System.out.print(sb);
    }
}
