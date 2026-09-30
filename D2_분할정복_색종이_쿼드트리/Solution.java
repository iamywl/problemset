import java.io.*;
import java.util.*;

public class Solution {
    static int blueCount;
    static int[][] paper;

    static void solve(int r, int c, int size) {
        int color = paper[r][c];
        boolean same = true;
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (paper[i][j] != color) {
                    same = false;
                    break;
                }
            }
            if (!same) break;
        }

        if (same) {
            if (color == 1) blueCount++;
            return;
        }

        int half = size / 2;
        solve(r, c, half);
        solve(r, c + half, half);
        solve(r + half, c, half);
        solve(r + half, c + half, half);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            paper = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    paper[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            blueCount = 0;
            solve(0, 0, n);
            sb.append("#").append(tc).append(" ").append(blueCount).append("\n");
        }
        System.out.print(sb);
    }
}
