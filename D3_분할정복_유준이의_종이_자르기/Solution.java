import java.io.*;
import java.util.*;

public class Solution {
    static int[][] grid;
    static int countMinus, countZero, countPlus;
    
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
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            countMinus = 0;
            countZero = 0;
            countPlus = 0;
            
            divide(0, 0, n);
            sb.append("#").append(tc).append(" ").append(countMinus).append(" ").append(countZero).append(" ").append(countPlus).append("\n");
        }
        System.out.print(sb);
    }
    
    private static void divide(int r, int c, int size) {
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
            if (first == -1) countMinus++;
            else if (first == 0) countZero++;
            else countPlus++;
            return;
        }
        
        int sub = size / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                divide(r + i * sub, c + j * sub, sub);
            }
        }
    }
}
