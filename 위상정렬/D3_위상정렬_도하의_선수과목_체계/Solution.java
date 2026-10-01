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

            List<List<Integer>> adj = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
            int[] inDegree = new int[n + 1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj.get(u).add(v);
                inDegree[v]++;
            }

            int[] semester = new int[n + 1];
            Arrays.fill(semester, 1);

            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0) q.offer(i);
            }

            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int next : adj.get(cur)) {
                    semester[next] = Math.max(semester[next], semester[cur] + 1);
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }

            StringBuilder ans = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (i > 1) ans.append(" ");
                ans.append(semester[i]);
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
