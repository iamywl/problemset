import java.io.*;
import java.util.*;

public class Solution {
    static int N, K, TR, TC;
    static int[][] grid;
    static int validCommands;
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
            K = Integer.parseInt(st.nextToken());
            TR = Integer.parseInt(st.nextToken());
            TC = Integer.parseInt(st.nextToken());

            grid = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) grid[i][j] = Integer.parseInt(st.nextToken());
            }

            validCommands = 0;
            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(validCommands).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int step, int r, int c) {
        // 맨해튼 거리 가지치기
        int dist = Math.abs(r - TR) + Math.abs(c - TC);
        if (dist > (K - step)) return;

        if (step == K) {
            if (r == TR && c == TC) validCommands++;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && grid[nr][nc] == 0) {
                dfs(step + 1, nr, nc);
            }
        }
    }
}
