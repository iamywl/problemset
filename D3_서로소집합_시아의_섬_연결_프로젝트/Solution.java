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
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            
            int[][] grid = new int[R][C];
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int total = R * C;
            parent = new int[total];
            for (int i = 0; i < total; i++) parent[i] = i;
            
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (grid[i][j] == 1) {
                        int u = i * C + j;
                        if (i + 1 < R && grid[i + 1][j] == 1) {
                            union(u, (i + 1) * C + j);
                        }
                        if (j + 1 < C && grid[i][j + 1] == 1) {
                            union(u, i * C + (j + 1));
                        }
                    }
                }
            }
            
            Set<Integer> islands = new HashSet<>();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (grid[i][j] == 1) {
                        islands.add(find(i * C + j));
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(islands.size()).append("\n");
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
