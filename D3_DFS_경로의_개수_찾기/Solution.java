import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int pathCount;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void dfs(int r, int c) {
        if (r == N - 1 && c == M - 1) {
            pathCount++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                if (map[nr][nc] == '0' && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    dfs(nr, nc);
                    visited[nr][nc] = false;
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
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            visited = new boolean[N][M];
            pathCount = 0;

            for (int r = 0; r < N; r++) {
                map[r] = br.readLine().trim().toCharArray();
            }

            if (map[0][0] == '0' && map[N - 1][M - 1] == '0') {
                visited[0][0] = true;
                dfs(0, 0);
            }

            System.out.println("#" + tc + " " + pathCount);
        }
    }
}
