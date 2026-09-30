import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static class Point { int r, c; public Point(int r, int c) { this.r = r; this.c = c; } }
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
            int maxH = 0;
            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    maxH = Math.max(maxH, map[r][c]);
                }
            }

            int ans = 1; // h=0일 때 최소 1개
            for (int h = 1; h < maxH; h++) {
                boolean[][] visited = new boolean[N][N];
                int cnt = 0;
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (map[r][c] > h && !visited[r][c]) {
                            cnt++;
                            Queue<Point> q = new ArrayDeque<>();
                            q.offer(new Point(r, c));
                            visited[r][c] = true;
                            while (!q.isEmpty()) {
                                Point cur = q.poll();
                                for (int i = 0; i < 4; i++) {
                                    int nr = cur.r + dr[i];
                                    int nc = cur.c + dc[i];
                                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                                        if (map[nr][nc] > h && !visited[nr][nc]) {
                                            visited[nr][nc] = true;
                                            q.offer(new Point(nr, nc));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                ans = Math.max(ans, cnt);
            }
            System.out.println("#" + tc + " " + ans);
        }
    }
}
