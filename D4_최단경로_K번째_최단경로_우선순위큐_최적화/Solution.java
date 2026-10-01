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
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            List<Edge>[] adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                adj[u].add(new Edge(v, w));
            }

            PriorityQueue<Integer>[] heap = new PriorityQueue[n + 1];
            for (int i = 1; i <= n; i++) {
                heap[i] = new PriorityQueue<>(Collections.reverseOrder());
            }

            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.offer(new Edge(1, 0));
            heap[1].offer(0);

            while (!pq.isEmpty()) {
                Edge cur = pq.poll();

                for (Edge nxt : adj[cur.to]) {
                    int nxtDist = cur.weight + nxt.weight;
                    if (heap[nxt.to].size() < k) {
                        heap[nxt.to].offer(nxtDist);
                        pq.offer(new Edge(nxt.to, nxtDist));
                    } else if (heap[nxt.to].peek() > nxtDist) {
                        heap[nxt.to].poll();
                        heap[nxt.to].offer(nxtDist);
                        pq.offer(new Edge(nxt.to, nxtDist));
                    }
                }
            }

            sb.append("#").append(tc).append("\n");
            for (int i = 1; i <= n; i++) {
                if (heap[i].size() < k) {
                    sb.append("-1\n");
                } else {
                    sb.append(heap[i].peek()).append("\n");
                }
            }
        }
        System.out.print(sb);
    }
}
