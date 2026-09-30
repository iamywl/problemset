import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] dist;
    static boolean[] visited;
    static int minCost;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            dist = new int[N + 1][N + 1];

            for (int i = 0; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j <= N; j++) {
                    dist[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[N + 1];
            minCost = Integer.MAX_VALUE;

            // 0번(유통센터)에서 출발
            permute(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(minCost).append("\n");
        }
        System.out.print(sb);
    }

    static void permute(int current, int count, int currentDist) {
        // 가지치기: 현재 거리가 이미 최솟값 이상이면 중단
        if (currentDist >= minCost) return;

        if (count == N) {
            int total = currentDist + dist[current][0];
            if (total < minCost) {
                minCost = total;
            }
            return;
        }

        for (int next = 1; next <= N; next++) {
            if (!visited[next]) {
                visited[next] = true;
                permute(next, count + 1, currentDist + dist[current][next]);
                visited[next] = false;
            }
        }
    }
}
