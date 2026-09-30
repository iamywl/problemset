import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static int[][] map;
    static List<int[]> emptyList;
    static List<int[]> virusList;
    static int maxSafeArea;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void dfsBuild(int startIdx, int count) {
        if (count == 3) {
            evalSafeArea();
            return;
        }

        for (int i = startIdx; i < emptyList.size(); i++) {
            int[] pos = emptyList.get(i);
            map[pos[0]][pos[1]] = 1;
            dfsBuild(i + 1, count + 1);
            map[pos[0]][pos[1]] = 0;
        }
    }

    static void evalSafeArea() {
        int[][] temp = new int[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                temp[r][c] = map[r][c];
                if (temp[r][c] == 2) {
                    q.offer(new int[]{r, c});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                    if (temp[nr][nc] == 0) {
                        temp[nr][nc] = 2;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        int safe = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (temp[r][c] == 0) safe++;
            }
        }
        maxSafeArea = Math.max(maxSafeArea, safe);
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
            emptyList = new ArrayList<>();
            virusList = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] == 0) emptyList.add(new int[]{r, c});
                    else if (map[r][c] == 2) virusList.add(new int[]{r, c});
                }
            }

            maxSafeArea = 0;
            dfsBuild(0, 0);
            System.out.println("#" + tc + " " + maxSafeArea);
        }
    }
}
