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
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int[][] map = new int[R][C];
            for (int r = 0; r < R; r++) {
                String row = br.readLine().trim();
                for (int c = 0; c < C; c++) {
                    map[r][c] = row.charAt(c) - '0';
                }
            }

            int[][] dist = new int[R][C];
            for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

            ArrayDeque<int[]> dq = new ArrayDeque<>();
            dq.addFirst(new int[]{0, 0});
            dist[0][0] = 0;

            while (!dq.isEmpty()) {
                int[] cur = dq.pollFirst();
                int r = cur[0];
                int c = cur[1];

                if (r == R - 1 && c == C - 1) break;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                    int cost = map[nr][nc];
                    if (dist[r][c] + cost < dist[nr][nc]) {
                        dist[nr][nc] = dist[r][c] + cost;
                        if (cost == 0) {
                            dq.addFirst(new int[]{nr, nc});
                        } else {
                            dq.addLast(new int[]{nr, nc});
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dist[R - 1][C - 1]).append("\n");
        }
        System.out.print(sb);
    }
}
