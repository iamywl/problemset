import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static class Point {
        int r, c;
        public Point(int r, int c) { this.r = r; this.c = c; }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[][] map = new int[N][N];
            int[][] time = new int[N][N];
            Queue<Point> q = new ArrayDeque<>();
            int emptyCount = 0;

            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    time[r][c] = -1;
                    if (map[r][c] == 2) {
                        q.offer(new Point(r, c));
                        time[r][c] = 0;
                    } else if (map[r][c] == 0) {
                        emptyCount++;
                    }
                }
            }

            if (emptyCount == 0) {
                System.out.println("#" + tc + " 0");
                continue;
            }

            int maxTime = 0;
            int infected = 0;

            while (!q.isEmpty()) {
                Point cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                        if (map[nr][nc] == 0 && time[nr][nc] == -1) {
                            time[nr][nc] = time[cur.r][cur.c] + 1;
                            maxTime = Math.max(maxTime, time[nr][nc]);
                            infected++;
                            q.offer(new Point(nr, nc));
                        }
                    }
                }
            }

            if (infected == emptyCount) {
                System.out.println("#" + tc + " " + maxTime);
            } else {
                System.out.println("#" + tc + " -1");
            }
        }
    }
}
