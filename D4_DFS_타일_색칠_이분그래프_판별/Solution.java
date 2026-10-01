import java.io.*;
import java.util.*;

public class Solution {
    static List<Integer>[] adj;
    static int[] color;
    static boolean isBipartite;

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
                int w = Integer.parseInt(st.nextToken());
                adj[u].add(w);
                adj[w].add(u);
            }

            color = new int[v + 1];
            isBipartite = true;

            for (int i = 1; i <= v; i++) {
                if (color[i] == 0) {
                    dfs(i, 1);
                    if (!isBipartite) break;
                }
            }

            sb.append("#").append(tc).append(" ").append(isBipartite ? "YES" : "NO").append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int cur, int c) {
        color[cur] = c;

        for (int next : adj[cur]) {
            if (color[next] == 0) {
                dfs(next, 3 - c);
                if (!isBipartite) return;
            } else if (color[next] == c) {
                isBipartite = false;
                return;
            }
        }
    }
}
