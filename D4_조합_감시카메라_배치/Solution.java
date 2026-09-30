import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, K;
    static int[][] grid;
    static List<int[]> emptySpots;
    static int[] pick;
    static int maxServerCount;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            grid = new int[N][M];
            emptySpots = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    if (grid[i][j] == 0) emptySpots.add(new int[]{i, j});
                }
            }

            int actualK = Math.min(K, emptySpots.size());
            pick = new int[actualK];
            maxServerCount = 0;

            if (actualK > 0) comb(0, 0, actualK);

            sb.append("#").append(tc).append(" ").append(maxServerCount).append("\n");
        }
        System.out.print(sb);
    }

    static void comb(int start, int depth, int targetK) {
        if (depth == targetK) {
            boolean[][] monitored = new boolean[N][M];
            for (int idx : pick) {
                int[] p = emptySpots.get(idx);
                monitored[p[0]][p[1]] = true;
                for (int d = 0; d < 4; d++) {
                    int cr = p[0] + dr[d];
                    int cc = p[1] + dc[d];
                    while (cr >= 0 && cr < N && cc >= 0 && cc < M) {
                        if (grid[cr][cc] == 2) break; // 벽
                        monitored[cr][cc] = true;
                        cr += dr[d];
                        cc += dc[d];
                    }
                }
            }

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 1 && monitored[i][j]) cnt++;
                }
            }
            if (cnt > maxServerCount) maxServerCount = cnt;
            return;
        }

        for (int i = start; i < emptySpots.size(); i++) {
            pick[depth] = i;
            comb(i + 1, depth + 1, targetK);
        }
    }
}
