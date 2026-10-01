import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int R, C;
    static char[][] board;
    static boolean[] usedAlpha;
    static int maxCount;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void dfs(int r, int c, int count) {
        maxCount = Math.max(maxCount, count);

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                int alphaIdx = board[nr][nc] - 'A';
                if (!usedAlpha[alphaIdx]) {
                    usedAlpha[alphaIdx] = true;
                    dfs(nr, nc, count + 1);
                    usedAlpha[alphaIdx] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            board = new char[R][C];
            for (int r = 0; r < R; r++) {
                board[r] = br.readLine().trim().toCharArray();
            }

            usedAlpha = new boolean[26];
            maxCount = 0;

            usedAlpha[board[0][0] - 'A'] = true;
            dfs(0, 0, 1);

            System.out.println("#" + tc + " " + maxCount);
        }
    }
}
