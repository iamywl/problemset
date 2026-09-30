import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    static int N;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int dfs(int r, int c) {
        visited[r][c] = true;
        int count = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                if (map[nr][nc] == '1' && !visited[nr][nc]) {
                    count += dfs(nr, nc);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new char[N][N];
            visited = new boolean[N][N];

            for (int r = 0; r < N; r++) {
                map[r] = br.readLine().trim().toCharArray();
            }

            List<Integer> complexes = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == '1' && !visited[r][c]) {
                        complexes.add(dfs(r, c));
                    }
                }
            }

            Collections.sort(complexes);
            System.out.println("#" + tc + " " + complexes.size());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < complexes.size(); i++) {
                if (i > 0) sb.append(" ");
                sb.append(complexes.get(i));
            }
            System.out.println(sb.toString());
        }
    }
}
