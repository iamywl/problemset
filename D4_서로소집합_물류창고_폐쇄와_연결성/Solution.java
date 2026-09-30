import java.io.*;
import java.util.*;

public class Solution {
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
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<List<Integer>> adj = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj.get(u).add(v);
                adj.get(v).add(u);
            }

            int[] order = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                order[i] = Integer.parseInt(st.nextToken());
            }

            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;

            boolean[] active = new boolean[n + 1];
            int[] ans = new int[n];
            int compCount = 0;

            // 역순 시뮬레이션: N-1번째 폐쇄 직후는 0개
            ans[n - 1] = 0;

            for (int i = n - 1; i >= 1; i--) {
                int u = order[i];
                active[u] = true;
                compCount++;

                for (int v : adj.get(u)) {
                    if (active[v]) {
                        if (union(u, v)) {
                            compCount--;
                        }
                    }
                }
                ans[i - 1] = compCount;
            }

            StringBuilder lineAns = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i > 0) lineAns.append(" ");
                lineAns.append(ans[i]);
            }

            sb.append("#").append(tc).append(" ").append(lineAns).append("\n");
        }
        System.out.print(sb);
    }
}
