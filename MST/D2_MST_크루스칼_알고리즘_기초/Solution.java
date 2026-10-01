import java.io.*;
import java.util.*;

public class Solution {
    static class Edge implements Comparable<Edge> {
        int u, v;
        long w;
        Edge(int u, int v, long w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.w, o.w);
        }
    }

    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
            return true;
        }
        return false;
    }

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

            Edge[] edges = new Edge[e];
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                edges[i] = new Edge(u, to, w);
            }

            Arrays.sort(edges);

            parent = new int[v + 1];
            for (int i = 1; i <= v; i++) parent[i] = i;

            long totalWeight = 0;
            int count = 0;

            for (Edge edge : edges) {
                if (union(edge.u, edge.v)) {
                    totalWeight += edge.w;
                    count++;
                    if (count == v - 1) break;
                }
            }

            sb.append("#").append(tc).append(" ").append(totalWeight).append("\n");
        }
        System.out.print(sb);
    }
}
