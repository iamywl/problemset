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
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i <= v; i++) adj.add(new ArrayList<>());
            int[] indegree = new int[v + 1];
            
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adj.get(u).add(to);
                indegree[to]++;
            }
            
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 1; i <= v; i++) {
                if (indegree[i] == 0) pq.add(i);
            }
            
            sb.append("#").append(tc);
            while (!pq.isEmpty()) {
                int u = pq.poll();
                sb.append(" ").append(u);
                for (int to : adj.get(u)) {
                    indegree[to]--;
                    if (indegree[to] == 0) pq.add(to);
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
