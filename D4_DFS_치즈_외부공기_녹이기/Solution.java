import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static int[][] map;
    static boolean[][] isAir;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void markAir(int r, int c) {
        isAir[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                if (map[nr][nc] == 0 && !isAir[nr][nc]) {
                    markAir(nr, nc);
                }
            }
        }
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

            map = new int[N][M];
            int cheeseCount = 0;
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] == 1) cheeseCount++;
                }
            }

            int hours = 0;
            while (cheeseCount > 0) {
                isAir = new boolean[N][M];
                markAir(0, 0);

                List<int[]> melting = new ArrayList<>();
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {
                        if (map[r][c] == 1) {
                            int airTouch = 0;
                            for (int i = 0; i < 4; i++) {
                                int nr = r + dr[i];
                                int nc = c + dc[i];
                                if (nr >= 0 && nr < N && nc >= 0 && nc < M && isAir[nr][nc]) {
                                    airTouch++;
                                }
                            }
                            if (airTouch >= 2) {
                                melting.add(new int[]{r, c});
                            }
                        }
                    }
                }

                for (int[] p : melting) {
                    map[p[0]][p[1]] = 0;
                    cheeseCount--;
                }
                hours++;
            }

            System.out.println("#" + tc + " " + hours);
        }
    }
}
