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
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            
            Edge[] edges = new Edge[e];
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                edges[i] = new Edge(u, to, w);
            }
            
            Arrays.sort(edges);
            parent = new int[v + 1];
            for (int i = 1; i <= v; i++) parent[i] = i;
            
            long totalW = 0;
            int count = 0;
            for (Edge edge : edges) {
                int ru = find(edge.u);
                int rv = find(edge.v);
                if (ru != rv) {
                    parent[rv] = ru;
                    totalW += edge.w;
                    count++;
                    if (count == v - 1) break;
                }
            }
            sb.append("#").append(tc).append(" ").append(totalW).append("\n");
        }
        System.out.print(sb);
    }
    
    private static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
}
