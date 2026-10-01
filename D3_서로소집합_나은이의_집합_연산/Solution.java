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
            int m = Integer.parseInt(st.nextToken());
            
            parent = new int[n + 1];
            for (int i = 0; i <= n; i++) parent[i] = i;
            
            sb.append("#").append(tc);
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int op = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                int ra = find(a);
                int rb = find(b);
                if (op == 0) {
                    if (ra != rb) parent[rb] = ra;
                } else {
                    sb.append(" ").append(ra == rb ? "YES" : "NO");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    
    private static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
}
