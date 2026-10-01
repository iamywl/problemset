import java.io.*;
import java.util.*;

public class Solution {
    static int[] parent;
    
    static class Edge implements Comparable<Edge> {
        int u, v;
        long w;
        Edge(int u, int v, long w) { this.u = u; this.v = v; this.w = w; }
        public int compareTo(Edge o) {
            return Long.compare(this.w, o.w);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            int[] xs = new int[n];
            int[] ys = new int[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                xs[i] = Integer.parseInt(st.nextToken());
                ys[i] = Integer.parseInt(st.nextToken());
            }
            
            // Prim's algorithm is O(N^2) which is faster and uses less memory on dense/complete graph
            boolean[] visited = new boolean[n];
            long[] minDist = new long[n];
            Arrays.fill(minDist, Long.MAX_VALUE);
            minDist[0] = 0;
            
            long totalCost = 0;
            for (int i = 0; i < n; i++) {
                long min = Long.MAX_VALUE;
                int u = -1;
                for (int j = 0; j < n; j++) {
                    if (!visited[j] && minDist[j] < min) {
                        min = minDist[j];
                        u = j;
                    }
                }
                
                visited[u] = true;
                totalCost += min;
                
                for (int v = 0; v < n; v++) {
                    if (!visited[v]) {
                        long d = Math.abs(xs[u] - xs[v]) + Math.abs(ys[u] - ys[v]);
                        if (d < minDist[v]) {
                            minDist[v] = d;
                        }
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(totalCost).append("\n");
        }
        System.out.print(sb);
    }
}
