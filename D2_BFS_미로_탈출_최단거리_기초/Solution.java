import java.io.*;
import java.util.*;

public class Solution {
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
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] map = new int[n][m];
            for (int r = 0; r < n; r++) {
                String row = br.readLine().trim();
                for (int c = 0; c < m; c++) {
                    map[r][c] = row.charAt(c) - '0';
                }
            }

            int[][] dist = new int[n][m];
            Deque<int[]> q = new ArrayDeque<>();

            q.offer(new int[]{0, 0});
            dist[0][0] = 1;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];

                if (r == n - 1 && c == m - 1) break;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                        if (map[nr][nc] == 1 && dist[nr][nc] == 0) {
                            dist[nr][nc] = dist[r][c] + 1;
                            q.offer(new int[]{nr, nc});
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dist[n - 1][m - 1]).append("\n");
        }
        System.out.print(sb);
    }
}
