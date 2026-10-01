import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static boolean[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int dfs(int r, int c) {
        visited[r][c] = true;
        int size = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 1 && nr <= N && nc >= 1 && nc <= M) {
                if (map[nr][nc] && !visited[nr][nc]) {
                    size += dfs(nr, nc);
                }
            }
        }
        return size;
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
            int K = Integer.parseInt(st.nextToken());

            map = new boolean[N + 1][M + 1];
            visited = new boolean[N + 1][M + 1];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                map[r][c] = true;
            }

            int maxSize = 0;
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= M; c++) {
                    if (map[r][c] && !visited[r][c]) {
                        maxSize = Math.max(maxSize, dfs(r, c));
                    }
                }
            }
            System.out.println("#" + tc + " " + maxSize);
        }
    }
}
