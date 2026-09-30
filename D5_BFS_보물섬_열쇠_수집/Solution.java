import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static class State {
        int r, c, mask, dist;
        public State(int r, int c, int mask, int dist) {
            this.r = r; this.c = c; this.mask = mask; this.dist = dist;
        }
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
            int startR = -1, startC = -1;
            int treasureR = -1, treasureC = -1;

            for (int r = 0; r < N; r++) {
                map[r] = br.readLine().trim().toCharArray();
                for (int c = 0; c < M; c++) {
                    if (map[r][c] == '2') { startR = r; startC = c; }
                    else if (map[r][c] == '3') { treasureR = r; treasureC = c; }
                }
            }

            boolean[][][] visited = new boolean[N][M][8];
            Queue<State> q = new ArrayDeque<>();
            q.offer(new State(startR, startC, 0, 0));
            visited[startR][startC][0] = true;

            int ans = -1;

            while (!q.isEmpty()) {
                State cur = q.poll();

                if (cur.r == treasureR && cur.c == treasureC && cur.mask == 7) {
                    ans = cur.dist;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                    char tile = map[nr][nc];
                    if (tile == '1') continue;
                    if (tile == '3' && cur.mask != 7) continue;

                    int nextMask = cur.mask;
                    if (tile == '4') nextMask |= 1;
                    else if (tile == '5') nextMask |= 2;
                    else if (tile == '6') nextMask |= 4;

                    if (!visited[nr][nc][nextMask]) {
                        visited[nr][nc][nextMask] = true;
                        q.offer(new State(nr, nc, nextMask, cur.dist + 1));
                    }
                }
            }
            System.out.println("#" + tc + " " + ans);
        }
    }
}
