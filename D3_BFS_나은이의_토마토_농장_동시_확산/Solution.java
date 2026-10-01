import java.io.*;
import java.util.*;

public class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[][] box = new int[n][m];
            Deque<int[]> q = new ArrayDeque<>();
            int unripe = 0;

            for (int r = 0; r < n; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < m; c++) {
                    box[r][c] = Integer.parseInt(st.nextToken());
                    if (box[r][c] == 1) {
                        q.offer(new int[]{r, c});
                    } else if (box[r][c] == 0) {
                        unripe++;
                    }
                }
            }

            if (unripe == 0) {
                sb.append("#").append(tc).append(" 0\n");
                continue;
            }

            int days = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                boolean changed = false;

                for (int i = 0; i < size; i++) {
                    int[] cur = q.poll();
                    int r = cur[0];
                    int c = cur[1];

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr >= 0 && nr < n && nc >= 0 && nc < m && box[nr][nc] == 0) {
                            box[nr][nc] = 1;
                            unripe--;
                            changed = true;
                            q.offer(new int[]{nr, nc});
                        }
                    }
                }
                if (changed) days++;
            }

            int ans = (unripe == 0) ? days : -1;
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
