import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

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

            ArrayList<Integer>[] adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
            int[] indeg = new int[n + 1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
                indeg[v]++;
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 1; i <= n; i++) {
                if (indeg[i] == 0) pq.offer(i);
            }

            sb.append("#").append(tc);
            while (!pq.isEmpty()) {
                int cur = pq.poll();
                sb.append(" ").append(cur);

                for (int next : adj[cur]) {
                    indeg[next]--;
                    if (indeg[next] == 0) {
                        pq.offer(next);
                    }
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
