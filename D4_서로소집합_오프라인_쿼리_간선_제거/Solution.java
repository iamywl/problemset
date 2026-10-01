import java.io.*;
import java.util.*;

public class Solution {
    static int[] parent;
    static int compCnt;
    
    static class Edge {
        int u, v;
        Edge(int u, int v) { this.u = u; this.v = v; }
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
            int q = Integer.parseInt(st.nextToken());
            
            Edge[] edges = new Edge[m + 1];
            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(u, v);
            }
            
            int[] removeIdx = new int[q];
            boolean[] isRemoved = new boolean[m + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < q; i++) {
                removeIdx[i] = Integer.parseInt(st.nextToken());
                isRemoved[removeIdx[i]] = true;
            }
            
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
            compCnt = n;
            
            for (int i = 1; i <= m; i++) {
                if (!isRemoved[i]) {
                    union(edges[i].u, edges[i].v);
                }
            }
            
            int[] ans = new int[q];
            for (int i = q - 1; i >= 0; i--) {
                ans[i] = compCnt;
                int eIdx = removeIdx[i];
                union(edges[eIdx].u, edges[eIdx].v);
            }
            
            sb.append("#").append(tc);
            for (int i = 0; i < q; i++) {
                sb.append(" ").append(ans[i]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    
    private static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    
    private static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra != rb) {
            parent[rb] = ra;
            compCnt--;
        }
    }
}
