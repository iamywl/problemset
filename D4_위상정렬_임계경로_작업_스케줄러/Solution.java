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

            long[] t = new long[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                t[i] = Long.parseLong(st.nextToken());
            }

            List<List<Integer>> adj = new ArrayList<>(n + 1);
            List<List<Integer>> revAdj = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) {
                adj.add(new ArrayList<>());
                revAdj.add(new ArrayList<>());
            }
            int[] inDeg = new int[n + 1];
            int[] outDeg = new int[n + 1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj.get(u).add(v);
                revAdj.get(v).add(u);
                inDeg[v]++;
                outDeg[u]++;
            }

            long[] early = new long[n + 1];
            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                if (inDeg[i] == 0) q.offer(i);
            }

            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int next : adj.get(cur)) {
                    early[next] = Math.max(early[next], early[cur] + t[cur]);
                    inDeg[next]--;
                    if (inDeg[next] == 0) {
                        q.offer(next);
                    }
                }
            }

            long totalTime = 0;
            for (int i = 1; i <= n; i++) {
                totalTime = Math.max(totalTime, early[i] + t[i]);
            }

            long[] late = new long[n + 1];
            Arrays.fill(late, Long.MAX_VALUE);
            for (int i = 1; i <= n; i++) {
                if (outDeg[i] == 0) {
                    late[i] = totalTime - t[i];
                    q.offer(i);
                }
            }

            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int prev : revAdj.get(cur)) {
                    late[prev] = Math.min(late[prev], late[cur] - t[prev]);
                    outDeg[prev]--;
                    if (outDeg[prev] == 0) {
                        q.offer(prev);
                    }
                }
            }

            int criticalCount = 0;
            for (int i = 1; i <= n; i++) {
                if (early[i] == late[i]) {
                    criticalCount++;
                }
            }

            sb.append("#").append(tc).append(" ").append(totalTime).append(" ").append(criticalCount).append("\n");
        }
        System.out.print(sb);
    }
}
