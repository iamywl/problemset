import java.io.*;
import java.util.*;

public class Solution {
    static int[] parent;
    
    static class Edge implements Comparable<Edge> {
        int u, v;
        long w;
        Edge(int u, int v, long w) { this.u = u; this.v = v; this.w = w; }
        public int compareTo(Edge o) {
            return Long.compare(this.w, o.w);
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
            
            Edge[] edges = new Edge[m];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                edges[i] = new Edge(u, v, w);
            }
            
            Arrays.sort(edges);
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
            
            long totalCost = 0;
            int count = 0;
            for (Edge e : edges) {
                int ru = find(e.u);
                int rv = find(e.v);
                if (ru != rv) {
                    parent[rv] = ru;
                    totalCost += e.w;
                    count++;
                    if (count == n - 1) break;
                }
            }
            sb.append("#").append(tc).append(" ").append(totalCost).append("\n");
        }
        System.out.print(sb);
    }
    
    private static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
}
