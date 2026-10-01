import java.io.*;
import java.util.*;

public class Solution {
    static int[] parent, size;
    
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
            size = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            
            sb.append("#").append(tc);
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                
                int ru = find(u);
                int rv = find(v);
                if (ru != rv) {
                    parent[rv] = ru;
                    size[ru] += size[rv];
                    sb.append(" ").append(size[ru]);
                } else {
                    sb.append(" ").append(size[ru]);
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
