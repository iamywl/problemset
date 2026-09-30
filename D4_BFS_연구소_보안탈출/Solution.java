import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * SW Expert Academy
 * 제출 언어: Java (Java 8 이상)
 * 알고리즘: 상태 공간 BFS (3D State BFS - visited[r][c][hasKey])
 */
public class Solution {

    static class State {
        int r, c, hasKey;
        public State(int r, int c, int hasKey) {
            this.r = r;
            this.c = c;
            this.hasKey = hasKey;
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
            int keyR = -1, keyC = -1;

            for (int r = 0; r < 16; r++) {
                String row = br.readLine().trim();
                for (int c = 0; c < 16; c++) {
                    map[r][c] = row.charAt(c);
                    if (map[r][c] == '2') {
                        startR = r; startC = c;
                    } else if (map[r][c] == '3') {
                        exitR = r; exitC = c;
                    } else if (map[r][c] == '4') {
                        keyR = r; keyC = c;
                    }
                }
            }

            if (startR == -1 || exitR == -1 || keyR == -1) {
                System.out.println("#" + tc + " 0");
                continue;
            }

            boolean[][][] visited = new boolean[16][16][2];
            Queue<State> queue = new ArrayDeque<>();

            queue.offer(new State(startR, startC, 0));
            visited[startR][startC][0] = true;

            int escaped = 0;

            while (!queue.isEmpty()) {
                State cur = queue.poll();

                if (cur.r == exitR && cur.c == exitC && cur.hasKey == 1) {
                    escaped = 1;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];

                    if (nr < 0 || nr >= 16 || nc < 0 || nc >= 16) continue;

                    char tile = map[nr][nc];
                    if (tile == '1') continue;
                    if (tile == '3' && cur.hasKey == 0) continue;

                    int nextHasKey = (cur.hasKey == 1 || tile == '4') ? 1 : 0;

                    if (!visited[nr][nc][nextHasKey]) {
                        visited[nr][nc][nextHasKey] = true;
                        queue.offer(new State(nr, nc, nextHasKey));
                    }
                }
            }

            System.out.println("#" + tc + " " + escaped);
        }
    }
}
