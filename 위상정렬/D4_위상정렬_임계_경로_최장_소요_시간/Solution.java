import java.io.*;
import java.util.*;

public class Solution {
    static class Edge {
        int to;
        long w;
        Edge(int to, long w) { this.to = to; this.w = w; }
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
            
            List<List<Edge>> adj = new ArrayList<>();
            for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
            int[] indegree = new int[n + 1];
            
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                adj.get(u).add(new Edge(to, w));
                indegree[to]++;
            }
            
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            long[] dist = new long[n + 1];
            Arrays.fill(dist, -1);
            dist[start] = 0;
            
            ArrayDeque<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                if (indegree[i] == 0) q.add(i);
            }
            
            while (!q.isEmpty()) {
                int u = q.poll();
                for (Edge e : adj.get(u)) {
                    if (dist[u] != -1 && dist[u] + e.w > dist[e.to]) {
                        dist[e.to] = dist[u] + e.w;
                    }
                    indegree[e.to]--;
                    if (indegree[e.to] == 0) q.add(e.to);
                }
            }
            sb.append("#").append(tc).append(" ").append(dist[end]).append("\n");
        }
        System.out.print(sb);
    }
}
