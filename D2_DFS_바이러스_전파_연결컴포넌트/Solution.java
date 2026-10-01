import java.io.*;
import java.util.*;

public class Solution {
    static List<Integer>[] adj;
    static boolean[] visited;
    static int infectedCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            int m = Integer.parseInt(br.readLine().trim());

            adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
                adj[v].add(u);
            }

            visited = new boolean[n + 1];
            infectedCount = 0;

            dfs(1);

            sb.append("#").append(tc).append(" ").append(infectedCount - 1).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        infectedCount++;

        for (int next : adj[cur]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}
