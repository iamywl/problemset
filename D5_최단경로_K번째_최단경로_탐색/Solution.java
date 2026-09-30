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

            List<List<Edge>> adj = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                adj.get(u).add(new Edge(v, w));
            }

            @SuppressWarnings("unchecked")
            PriorityQueue<Long>[] distHeap = new PriorityQueue[n + 1];
            for (int i = 1; i <= n; i++) {
                distHeap[i] = new PriorityQueue<>(k, Collections.reverseOrder());
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            distHeap[1].offer(0L);
            pq.offer(new Node(1, 0));

            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                if (cur.dist > distHeap[cur.v].peek()) continue;

                for (Edge edge : adj.get(cur.v)) {
                    long nextDist = cur.dist + edge.w;
                    PriorityQueue<Long> nextHeap = distHeap[edge.to];

                    if (nextHeap.size() < k) {
                        nextHeap.offer(nextDist);
                        pq.offer(new Node(edge.to, nextDist));
                    } else if (nextDist < nextHeap.peek()) {
                        nextHeap.poll();
                        nextHeap.offer(nextDist);
                        pq.offer(new Node(edge.to, nextDist));
                    }
                }
            }

            StringBuilder ans = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (i > 1) ans.append(" ");
                if (distHeap[i].size() == k) {
                    ans.append(distHeap[i].peek());
                } else {
                    ans.append(-1);
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
