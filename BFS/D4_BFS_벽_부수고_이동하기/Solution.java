import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static class State {
        int r, c, broken, dist;
        public State(int r, int c, int broken, int dist) {
            this.r = r; this.c = c; this.broken = broken; this.dist = dist;
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
            for (int r = 0; r < N; r++) {
                map[r] = br.readLine().trim().toCharArray();
            }

            boolean[][][] visited = new boolean[N][M][2];
            Queue<State> q = new ArrayDeque<>();
            q.offer(new State(0, 0, 0, 1));
            visited[0][0][0] = true;

            int ans = -1;

            while (!q.isEmpty()) {
                State cur = q.poll();
                if (cur.r == N - 1 && cur.c == M - 1) {
                    ans = cur.dist;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                    if (map[nr][nc] == '0' && !visited[nr][nc][cur.broken]) {
                        visited[nr][nc][cur.broken] = true;
                        q.offer(new State(nr, nc, cur.broken, cur.dist + 1));
                    } else if (map[nr][nc] == '1' && cur.broken == 0 && !visited[nr][nc][1]) {
                        visited[nr][nc][1] = true;
                        q.offer(new State(nr, nc, 1, cur.dist + 1));
                    }
                }
            }

            System.out.println("#" + tc + " " + ans);
        }
    }
}
