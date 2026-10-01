import java.io.*;
import java.util.*;

public class Solution {
    static int[] parent;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
            
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                union(u, v);
            }
            
            sb.append("#").append(tc);
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                sb.append(" ").append(find(x) == find(y) ? "1" : "0");
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
        if (ra != rb) parent[rb] = ra;
    }
}
