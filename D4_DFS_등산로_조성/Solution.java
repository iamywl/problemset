import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, K;
    static int[][] map;
    static boolean[][] visited;
    static int maxLen;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void dfs(int r, int c, int len, boolean usedSkill) {
        maxLen = Math.max(maxLen, len);

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

            if (map[nr][nc] < map[r][c]) {
                visited[nr][nc] = true;
                dfs(nr, nc, len + 1, usedSkill);
                visited[nr][nc] = false;
            } else if (!usedSkill) {
                for (int cut = 1; cut <= K; cut++) {
                    if (map[nr][nc] - cut < map[r][c]) {
                        int orig = map[nr][nc];
                        map[nr][nc] -= cut;
                        visited[nr][nc] = true;

                        dfs(nr, nc, len + 1, true);

                        visited[nr][nc] = false;
                        map[nr][nc] = orig;
                    }
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
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visited = new boolean[N][N];
            int top = 0;

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    top = Math.max(top, map[r][c]);
                }
            }

            maxLen = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == top) {
                        visited[r][c] = true;
                        dfs(r, c, 1, false);
                        visited[r][c] = false;
                    }
                }
            }

            System.out.println("#" + tc + " " + maxLen);
        }
    }
}
