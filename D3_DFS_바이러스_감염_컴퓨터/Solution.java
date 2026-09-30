import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static List<Integer>[] adj;
    static boolean[] visited;

    static int dfs(int u) {
        visited[u] = true;
        int count = 1;
        for (int v : adj[u]) {
            if (!visited[v]) {
                count += dfs(v);
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
            M = Integer.parseInt(br.readLine().trim());

            adj = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
            visited = new boolean[N + 1];

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
                adj[v].add(u);
            }

            int infected = dfs(1) - 1; // 1번 제외
            System.out.println("#" + tc + " " + infected);
        }
    }
}
