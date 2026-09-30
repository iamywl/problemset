import java.io.*;
import java.util.*;

public class Solution {
    static int R, C, K;
    static int[][] grid;
    static int totalCells;
    static int[] chosen;
    static int maxHarvest;
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            grid = new int[R][C];
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    grid[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            totalCells = R * C;
            chosen = new int[K];
            maxHarvest = 0;

            comb(0, 0);

            sb.append("#").append(tc).append(" ").append(maxHarvest).append("\n");
        }
        System.out.print(sb);
    }

    static void comb(int start, int depth) {
        if (depth == K) {
            evaluate();
            return;
        }

        for (int i = start; i < totalCells; i++) {
            chosen[depth] = i;
            comb(i + 1, depth + 1);
        }
    }

    static void evaluate() {
        boolean[][] visited = new boolean[R][C];
        int sum = 0;

        for (int idx : chosen) {
            int cr = idx / C;
            int cc = idx % C;

            for (int d = 0; d < 5; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                    if (!visited[nr][nc]) {
                        visited[nr][nc] = true;
                        sum += grid[nr][nc];
                    }
                }
            }
        }

        if (sum > maxHarvest) {
            maxHarvest = sum;
        }
    }
}
