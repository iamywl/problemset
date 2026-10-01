import java.io.*;
import java.util.*;

public class Solution {
    static List<Integer>[] adj;
    static int[] state; // 0: unvisited, 1: visiting, 2: visited
    static boolean hasCycle;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            adj = new ArrayList[v + 1];
            for (int i = 1; i <= v; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adj[u].add(to);
            }

            state = new int[v + 1];
            hasCycle = false;

            for (int i = 1; i <= v; i++) {
                if (state[i] == 0) {
                    dfs(i);
                    if (hasCycle) break;
                }
            }

            sb.append("#").append(tc).append(" ").append(hasCycle ? 1 : 0).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int cur) {
        state[cur] = 1;

        for (int next : adj[cur]) {
            if (state[next] == 1) {
                hasCycle = true;
                return;
            } else if (state[next] == 0) {
                dfs(next);
                if (hasCycle) return;
            }
        }

        state[cur] = 2;
    }
}
