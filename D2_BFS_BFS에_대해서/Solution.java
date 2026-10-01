import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<Integer>[] adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
                adj[v].add(u);
            }

            for (int i = 1; i <= n; i++) {
                Collections.sort(adj[i]);
            }

            boolean[] visited = new boolean[n + 1];
            Deque<Integer> q = new ArrayDeque<>();
            List<Integer> order = new ArrayList<>();

            q.offer(1);
            visited[1] = true;

            while (!q.isEmpty()) {
                int cur = q.poll();
                order.add(cur);

                for (int next : adj[cur]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }

            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < order.size(); i++) {
                sb.append(order.get(i)).append(i == order.size() - 1 ? "" : " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
