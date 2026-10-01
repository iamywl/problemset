import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[] P, D;
    static boolean[] visited;
    static int minDelay;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            P = new int[N];
            D = new int[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                P[i] = Integer.parseInt(st.nextToken());
                D[i] = Integer.parseInt(st.nextToken());
            }

            visited = new boolean[N];
            minDelay = Integer.MAX_VALUE;
            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(minDelay).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int depth, int curTime, int curDelay) {
        if (curDelay >= minDelay) return;
        if (depth == N) {
            if (curDelay < minDelay) minDelay = curDelay;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int nTime = curTime + P[i];
                int delay = Math.max(0, nTime - D[i]);
                dfs(depth + 1, nTime, curDelay + delay);
                visited[i] = false;
            }
        }
    }
}
