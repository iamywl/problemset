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
            int k = Integer.parseInt(st.nextToken());
            
            long[] times = new long[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) times[i] = Long.parseLong(st.nextToken());
            
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
            int[] indegree = new int[n + 1];
            
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adj.get(u).add(to);
                indegree[to]++;
            }
            int target = Integer.parseInt(br.readLine().trim());
            
            long[] dp = new long[n + 1];
            ArrayDeque<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                if (indegree[i] == 0) {
                    dp[i] = times[i];
                    q.add(i);
                }
            }
            
            while (!q.isEmpty()) {
                int u = q.poll();
                for (int to : adj.get(u)) {
                    if (dp[u] + times[to] > dp[to]) {
                        dp[to] = dp[u] + times[to];
                    }
                    indegree[to]--;
                    if (indegree[to] == 0) q.add(to);
                }
            }
            sb.append("#").append(tc).append(" ").append(dp[target]).append("\n");
        }
        System.out.print(sb);
    }
}
