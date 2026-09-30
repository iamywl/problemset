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
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            char[][] map = new char[N][M];
            for (int r = 0; r < N; r++) {
                map[r] = br.readLine().trim().toCharArray();
            }

            int[][] dist = new int[N][M];
            Queue<Point> q = new ArrayDeque<>();
            q.offer(new Point(0, 0));
            dist[0][0] = 1;

            while (!q.isEmpty()) {
                Point cur = q.poll();
                if (cur.r == N - 1 && cur.c == M - 1) break;

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                        if (map[nr][nc] == '1' && dist[nr][nc] == 0) {
                            dist[nr][nc] = dist[cur.r][cur.c] + 1;
                            q.offer(new Point(nr, nc));
                        }
                    }
                }
            }
            System.out.println("#" + tc + " " + dist[N - 1][M - 1]);
        }
    }
}
