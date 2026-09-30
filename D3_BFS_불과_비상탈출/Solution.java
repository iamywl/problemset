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
            StringTokenizer st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            char[][] map = new char[R][C];
            int[][] fireTime = new int[R][C];
            int[][] jTime = new int[R][C];

            Queue<Point> fireQ = new ArrayDeque<>();
            Queue<Point> jQ = new ArrayDeque<>();

            for (int r = 0; r < R; r++) {
                String row = br.readLine().trim();
                for (int c = 0; c < C; c++) {
                    map[r][c] = row.charAt(c);
                    fireTime[r][c] = -1;
                    jTime[r][c] = -1;
                    if (map[r][c] == 'F') {
                        fireQ.offer(new Point(r, c));
                        fireTime[r][c] = 0;
                    } else if (map[r][c] == 'J') {
                        jQ.offer(new Point(r, c));
                        jTime[r][c] = 0;
                    }
                }
            }

            // 1. Fire BFS
            while (!fireQ.isEmpty()) {
                Point cur = fireQ.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                        if (map[nr][nc] != '#' && fireTime[nr][nc] == -1) {
                            fireTime[nr][nc] = fireTime[cur.r][cur.c] + 1;
                            fireQ.offer(new Point(nr, nc));
                        }
                    }
                }
            }

            // 2. Jihoon BFS
            int ans = -1;
            while (!jQ.isEmpty()) {
                Point cur = jQ.poll();

                // 탈출 조건 (경계 도달)
                if (cur.r == 0 || cur.r == R - 1 || cur.c == 0 || cur.c == C - 1) {
                    ans = jTime[cur.r][cur.c] + 1;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                        if (map[nr][nc] != '#' && jTime[nr][nc] == -1) {
                            int nextJ = jTime[cur.r][cur.c] + 1;
                            if (fireTime[nr][nc] == -1 || fireTime[nr][nc] > nextJ) {
                                jTime[nr][nc] = nextJ;
                                jQ.offer(new Point(nr, nc));
                            }
                        }
                    }
                }
            }

            if (ans != -1) {
                System.out.println("#" + tc + " " + ans);
            } else {
                System.out.println("#" + tc + " IMPOSSIBLE");
            }
        }
    }
}
