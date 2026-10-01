import java.io.*;
import java.util.*;

public class Solution {
    static int[] parent;
    static long[] diff;
    
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
            diff = new long[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
            
            sb.append("#").append(tc);
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                if (type.equals("!")) {
                    long w = Long.parseLong(st.nextToken());
                    int ra = find(a);
                    int rb = find(b);
                    if (ra != rb) {
                        diff[rb] = diff[a] + w - diff[b];
                        parent[rb] = ra;
                    }
                } else {
                    int ra = find(a);
                    int rb = find(b);
                    if (ra == rb) {
                        sb.append(" ").append(diff[b] - diff[a]);
                    } else {
                        sb.append(" UNKNOWN");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    
    private static int find(int x) {
        if (parent[x] != x) {
            int p = parent[x];
            parent[x] = find(p);
            diff[x] += diff[p];
        }
        return parent[x];
    }
}
