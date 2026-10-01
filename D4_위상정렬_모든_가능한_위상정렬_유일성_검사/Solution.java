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
            
            ArrayDeque<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= v; i++) {
                if (indegree[i] == 0) q.add(i);
            }
            
            int count = 0;
            boolean multiple = false;
            
            while (!q.isEmpty()) {
                if (q.size() > 1) multiple = true;
                int u = q.poll();
                count++;
                for (int to : adj.get(u)) {
                    indegree[to]--;
                    if (indegree[to] == 0) q.add(to);
                }
            }
            
            String ans;
            if (count < v) {
                ans = "CYCLE";
            } else if (multiple) {
                ans = "MULTIPLE";
            } else {
                ans = "UNIQUE";
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
