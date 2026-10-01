import java.io.*;
import java.util.*;

public class Solution {
    static int[][] map;

    static boolean isUniform(int r, int c, int sz) {
        int color = map[r][c];
        for (int i = r; i < r + sz; i++) {
            for (int j = c; j < c + sz; j++) {
                if (map[i][j] != color) return false;
            }
        }
        return true;
    }

    static void compress(int r, int c, int sz, StringBuilder sb) {
        if (isUniform(r, c, sz)) {
            sb.append(map[r][c]);
            return;
        }
        sb.append("(");
        int half = sz / 2;
        compress(r, c, half, sb);
        compress(r, c + half, half, sb);
        compress(r + half, c, half, sb);
        compress(r + half, c + half, half, sb);
        sb.append(")");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder out = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                String row = br.readLine().trim();
                for (int j = 0; j < n; j++) {
                    map[i][j] = row.charAt(j) - '0';
                }
            }

            StringBuilder sb = new StringBuilder();
            compress(0, 0, n, sb);
            out.append("#").append(tc).append(" ").append(sb.toString()).append("\n");
        }
        System.out.print(out);
    }
}
