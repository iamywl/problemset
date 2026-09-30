import java.io.*;
import java.util.*;

public class Solution {
    static class Planet {
        int id;
        long x, y, z;
        Planet(int id, long x, long y, long z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

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
            int n = Integer.parseInt(br.readLine().trim());
            Planet[] planets = new Planet[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                long x = Long.parseLong(st.nextToken());
                long y = Long.parseLong(st.nextToken());
                long z = Long.parseLong(st.nextToken());
                planets[i] = new Planet(i, x, y, z);
            }

            List<Edge> edges = new ArrayList<>(3 * n);

            // X축 정렬
            Arrays.sort(planets, Comparator.comparingLong(p -> p.x));
            for (int i = 0; i < n - 1; i++) {
                edges.add(new Edge(planets[i].id, planets[i + 1].id, Math.abs(planets[i].x - planets[i + 1].x)));
            }

            // Y축 정렬
            Arrays.sort(planets, Comparator.comparingLong(p -> p.y));
            for (int i = 0; i < n - 1; i++) {
                edges.add(new Edge(planets[i].id, planets[i + 1].id, Math.abs(planets[i].y - planets[i + 1].y)));
            }

            // Z축 정렬
            Arrays.sort(planets, Comparator.comparingLong(p -> p.z));
            for (int i = 0; i < n - 1; i++) {
                edges.add(new Edge(planets[i].id, planets[i + 1].id, Math.abs(planets[i].z - planets[i + 1].z)));
            }

            Collections.sort(edges);

            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;

            long totalCost = 0;
            int count = 0;

            for (Edge edge : edges) {
                if (union(edge.u, edge.v)) {
                    totalCost += edge.w;
                    count++;
                    if (count == n - 1) break;
                }
            }

            sb.append("#").append(tc).append(" ").append(totalCost).append("\n");
        }
        System.out.print(sb);
    }
}
