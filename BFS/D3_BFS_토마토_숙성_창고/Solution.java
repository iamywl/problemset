import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static class Point3D {
        int h, r, c;
        public Point3D(int h, int r, int c) { this.h = h; this.r = r; this.c = c; }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());

        int[] dh = {-1, 1, 0, 0, 0, 0};
        int[] dr = {0, 0, -1, 1, 0, 0};
        int[] dc = {0, 0, 0, 0, -1, 1};

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            int[][][] box = new int[H][N][M];
            int[][][] days = new int[H][N][M];
            Queue<Point3D> q = new ArrayDeque<>();
            int unripe = 0;

            for (int h = 0; h < H; h++) {
                for (int r = 0; r < N; r++) {
                    st = new StringTokenizer(br.readLine());
                    for (int c = 0; c < M; c++) {
                        box[h][r][c] = Integer.parseInt(st.nextToken());
                        days[h][r][c] = -1;
                        if (box[h][r][c] == 1) {
                            q.offer(new Point3D(h, r, c));
                            days[h][r][c] = 0;
                        } else if (box[h][r][c] == 0) {
                            unripe++;
                        }
                    }
                }
            }

            if (unripe == 0) {
                System.out.println("#" + tc + " 0");
                continue;
            }

            int maxDay = 0;
            int ripened = 0;

            while (!q.isEmpty()) {
                Point3D cur = q.poll();
                for (int i = 0; i < 6; i++) {
                    int nh = cur.h + dh[i];
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];

                    if (nh >= 0 && nh < H && nr >= 0 && nr < N && nc >= 0 && nc < M) {
                        if (box[nh][nr][nc] == 0 && days[nh][nr][nc] == -1) {
                            days[nh][nr][nc] = days[cur.h][cur.r][cur.c] + 1;
                            maxDay = Math.max(maxDay, days[nh][nr][nc]);
                            ripened++;
                            q.offer(new Point3D(nh, nr, nc));
                        }
                    }
                }
            }

            if (ripened == unripe) {
                System.out.println("#" + tc + " " + maxDay);
            } else {
                System.out.println("#" + tc + " -1");
            }
        }
    }
}
