import java.io.*;
import java.util.*;

public class Solution {
    static class Edge {
        int to;
        long w;
        Edge(int to, long w) { this.to = to; this.w = w; }
    }
    
    static List<List<Edge>> adj;
    static int target;
    static long maxPathW;
    static boolean found;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            adj = new ArrayList<>();
            for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
            
            long initWeight = 0;
            for (int i = 0; i < n - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                adj.get(u).add(new Edge(v, w));
                adj.get(v).add(new Edge(u, w));
                initWeight += w;
            }
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nu = Integer.parseInt(st.nextToken());
            int nv = Integer.parseInt(st.nextToken());
            long nw = Long.parseLong(st.nextToken());
            
            target = nv;
            maxPathW = 0;
            found = false;
            boolean[] visited = new boolean[n + 1];
            dfs(nu, 0, visited);
            
            long maxCycleW = Math.max(nw, maxPathW);
            long newMstWeight = initWeight + nw - maxCycleW;
            sb.append("#").append(tc).append(" ").append(newMstWeight).append("\n");
        }
        System.out.print(sb);
    }
    
    private static void dfs(int u, long curMax, boolean[] visited) {
        visited[u] = true;
        if (u == target) {
            maxPathW = curMax;
            found = true;
            return;
        }
        for (Edge e : adj.get(u)) {
            if (!visited[e.to]) {
                dfs(e.to, Math.max(curMax, e.w), visited);
                if (found) return;
            }
        }
    }
}
