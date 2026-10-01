import java.io.*;
import java.util.*;

public class Solution {
    static int r, c, maxSteps;
    static char[][] board;
    static boolean[] used;
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
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            board = new char[r][c];
            for (int i = 0; i < r; i++) {
                board[i] = br.readLine().trim().toCharArray();
            }

            used = new boolean[26];
            maxSteps = 0;

            used[board[0][0] - 'A'] = true;
            dfs(0, 0, 1);

            sb.append("#").append(tc).append(" ").append(maxSteps).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int cr, int cc, int count) {
        if (count > maxSteps) maxSteps = count;
        if (maxSteps == 26) return;

        for (int d = 0; d < 4; d++) {
            int nr = cr + dr[d];
            int nc = cc + dc[d];

            if (nr >= 0 && nr < r && nc >= 0 && nc < c) {
                int chIdx = board[nr][nc] - 'A';
                if (!used[chIdx]) {
                    used[chIdx] = true;
                    dfs(nr, nc, count + 1);
                    used[chIdx] = false;
                }
            }
        }
    }
}
