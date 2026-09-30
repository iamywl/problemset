import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * SW Expert Academy
 * [S/W 문제해결 응용] 미로 최단경로 (D4)
 * 제출 언어: Java (Java 8 이상)
 * 알고리즘: BFS (너비 우선 탐색)
 */
public class Solution {

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String tc = line;
            char[][] map = new char[16][16];
            int startR = -1, startC = -1;
            int exitR = -1, exitC = -1;

            for (int r = 0; r < 16; r++) {
                String row = br.readLine().trim();
                for (int c = 0; c < 16; c++) {
                    map[r][c] = row.charAt(c);
                    if (map[r][c] == '2') {
                        startR = r;
                        startC = c;
                    } else if (map[r][c] == '3') {
                        exitR = r;
                        exitC = c;
                    }
                }
            }

            if (startR == -1 || exitR == -1) {
                System.out.println("#" + tc + " -1");
                continue;
            }

            int[][] dist = new int[16][16];
            for (int r = 0; r < 16; r++) {
                for (int c = 0; c < 16; c++) {
                    dist[r][c] = -1;
                }
            }

            Queue<Point> queue = new ArrayDeque<>();
            queue.offer(new Point(startR, startC));
            dist[startR][startC] = 0;

            int ans = -1;

            while (!queue.isEmpty()) {
                Point cur = queue.poll();

                if (cur.r == exitR && cur.c == exitC) {
                    ans = dist[cur.r][cur.c];
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];

                    if (nr < 0 || nr >= 16 || nc < 0 || nc >= 16) continue;

                    char tile = map[nr][nc];
                    if (tile != '1' && dist[nr][nc] == -1) {
                        dist[nr][nc] = dist[cur.r][cur.c] + 1;
                        queue.offer(new Point(nr, nc));
                    }
                }
            }

            System.out.println("#" + tc + " " + ans);
        }
    }
}
