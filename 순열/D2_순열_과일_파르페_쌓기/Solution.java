import java.io.*;
import java.util.*;

public class Solution {
    static int N, R, M;
    static long ans;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            ans = 0;
            visited = new boolean[N + 1];

            // 1층에 1 ~ M번 과일 배치
            for (int f = 1; f <= M; f++) {
                visited[f] = true;
                dfs(1);
                visited[f] = false;
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int depth) {
        if (depth == R) {
            ans++;
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}
