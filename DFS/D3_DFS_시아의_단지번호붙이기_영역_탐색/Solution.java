import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            map = new int[n][n];
            visited = new boolean[n][n];

            for (int r = 0; r < n; r++) {
                String row = br.readLine().trim();
                for (int c = 0; c < n; c++) {
                    map[r][c] = row.charAt(c) - '0';
                }
            }

            List<Integer> complexes = new ArrayList<>();
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (map[r][c] == 1 && !visited[r][c]) {
                        complexes.add(dfs(r, c));
                    }
                }
            }

            Collections.sort(complexes);

            sb.append("#").append(tc).append(" ").append(complexes.size()).append("\n");
            for (int count : complexes) {
                sb.append(count).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static int dfs(int r, int c) {
        visited[r][c] = true;
        int cnt = 1;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                if (map[nr][nc] == 1 && !visited[nr][nc]) {
                    cnt += dfs(nr, nc);
                }
            }
        }
        return cnt;
    }
}
