import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        int T = Integer.parseInt(line.trim());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = null;
            while (st == null || !st.hasMoreTokens()) {
                line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
            }
            if (st == null) break;

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            int sink = Integer.parseInt(st.nextToken());

            List<Integer>[] adj = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                adj[i] = new ArrayList<>();
            }

            int[][] capacity = new int[V + 1][V + 1];
            int[][] flow = new int[V + 1][V + 1];

            for (int i = 0; i < E; i++) {
                while (!st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if (capacity[u][v] == 0 && capacity[v][u] == 0) {
                    adj[u].add(v);
                    adj[v].add(u);
                }
                capacity[u][v] += c;
            }

            int totalFlow = 0;
            int[] parent = new int[V + 1];
            int[] queue = new int[V + 1];

            while (true) {
                Arrays.fill(parent, -1);
                int head = 0;
                int tail = 0;

                queue[tail++] = S;
                parent[S] = S;

                while (head < tail && parent[sink] == -1) {
                    int curr = queue[head++];
                    for (int nxt : adj[curr]) {
                        if (parent[nxt] == -1 && capacity[curr][nxt] - flow[curr][nxt] > 0) {
                            parent[nxt] = curr;
                            queue[tail++] = nxt;
                            if (nxt == sink) break;
                        }
                    }
                }

                if (parent[sink] == -1) {
                    break;
                }

                int push = Integer.MAX_VALUE;
                int curr = sink;
                while (curr != S) {
                    int p = parent[curr];
                    push = Math.min(push, capacity[p][curr] - flow[p][curr]);
                    curr = p;
                }

                curr = sink;
                while (curr != S) {
                    int p = parent[curr];
                    flow[p][curr] += push;
                    flow[curr][p] -= push;
                    curr = p;
                }

                totalFlow += push;
            }

            sb.append("#").append(tc).append(" ").append(totalFlow).append("\n");
        }
        System.out.print(sb.toString());
    }
}
