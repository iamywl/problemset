import java.io.*;
import java.util.*;

public class Solution {
    static int[][] grid;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                String row = br.readLine().trim();
                for (int j = 0; j < n; j++) {
                    grid[i][j] = row.charAt(j) - '0';
                }
            }
            
            String compressed = compress(0, 0, n);
            sb.append("#").append(tc).append(" ").append(compressed).append("\n");
        }
        System.out.print(sb);
    }
    
    private static String compress(int r, int c, int size) {
        int first = grid[r][c];
        boolean uniform = true;
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (grid[i][j] != first) {
                    uniform = false;
                    break;
                }
            }
            if (!uniform) break;
        }
        
        if (uniform) {
            return String.valueOf(first);
        }
        
        int half = size / 2;
        return "(" + compress(r, c, half)
                   + compress(r, c + half, half)
                   + compress(r + half, c, half)
                   + compress(r + half, c + half, half) + ")";
    }
}
