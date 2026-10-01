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
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            char[][] map = new char[r][c];
            for (int i = 0; i < r; i++) {
                map[i] = br.readLine().trim().toCharArray();
            }

            int maxDist = 0;

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (map[i][j] == 'L') {
                        int d = bfs(i, j, r, c, map);
                        if (d > maxDist) maxDist = d;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(maxDist).append("\n");
        }
        System.out.print(sb);
    }

    private static int bfs(int startR, int startC, int r, int c, char[][] map) {
        int[][] dist = new int[r][c];
        for (int i = 0; i < r; i++) Arrays.fill(dist[i], -1);

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startR, startC});
        dist[startR][startC] = 0;

        int localMax = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];

            if (dist[cr][cc] > localMax) localMax = dist[cr][cc];

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr >= 0 && nr < r && nc >= 0 && nc < c) {
                    if (map[nr][nc] == 'L' && dist[nr][nc] == -1) {
                        dist[nr][nc] = dist[cr][cc] + 1;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        return localMax;
    }
}
