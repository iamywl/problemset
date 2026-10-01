import java.io.*;
import java.util.*;

public class Solution {
    static class Node implements Comparable<Node> {
        int to;
        long weight;
        Node(int to, long weight) { this.to = to; this.weight = weight; }
        public int compareTo(Node o) {
            return Long.compare(this.weight, o.weight);
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
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            
            List<List<Node>> adj = new ArrayList<>();
            for (int i = 0; i <= v; i++) adj.add(new ArrayList<>());
            
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                adj.get(u).add(new Node(to, w));
                adj.get(to).add(new Node(u, w));
            }
            
            boolean[] visited = new boolean[v + 1];
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(1, 0));
            
            long totalWeight = 0;
            int count = 0;
            
            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if (visited[cur.to]) continue;
                
                visited[cur.to] = true;
                totalWeight += cur.weight;
                count++;
                if (count == v) break;
                
                for (Node next : adj.get(cur.to)) {
                    if (!visited[next.to]) {
                        pq.add(next);
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(totalWeight).append("\n");
        }
        System.out.print(sb);
    }
}
