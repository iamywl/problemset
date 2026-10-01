import java.io.*;
import java.util.*;

public class Solution {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) { this.to = to; this.weight = weight; }
    }

    static List<Edge>[] adj;
    static boolean[] visited;
    static int maxDist, farthestNode;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int v = Integer.parseInt(br.readLine().trim());
            adj = new ArrayList[v + 1];
            for (int i = 1; i <= v; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < v - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                adj[u].add(new Edge(to, w));
                adj[to].add(new Edge(u, w));
            }

            // 1st DFS: find farthest from 1
            visited = new boolean[v + 1];
            maxDist = 0;
            farthestNode = 1;
            dfs(1, 0);

            // 2nd DFS: find farthest from farthestNode
            visited = new boolean[v + 1];
            maxDist = 0;
            dfs(farthestNode, 0);

            sb.append("#").append(tc).append(" ").append(maxDist).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int cur, int dist) {
        visited[cur] = true;
        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = cur;
        }

        for (Edge e : adj[cur]) {
            if (!visited[e.to]) {
                dfs(e.to, dist + e.weight);
            }
        }
    }
}
