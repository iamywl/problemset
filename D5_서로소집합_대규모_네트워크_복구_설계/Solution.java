import java.io.*;
import java.util.*;

public class Solution {
    static int[] parent;
    static int[] rank;

    static class History {
        int u, v;
        boolean rankIncreased;
        History(int u, int v, boolean rankIncreased) {
            this.u = u;
            this.v = v;
            this.rankIncreased = rankIncreased;
        }
    }

    static Deque<History> historyStack = new ArrayDeque<>();

    static int find(int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return;
        }

        if (rank[rootA] < rank[rootB]) {
            int tmp = rootA; rootA = rootB; rootB = tmp;
        }

        parent[rootB] = rootA;
        boolean inc = false;
        if (rank[rootA] == rank[rootB]) {
            rank[rootA]++;
            inc = true;
        }
        historyStack.push(new History(rootA, rootB, inc));
    }

    static void rollback(int k) {
        while (k > 0 && !historyStack.isEmpty()) {
            History h = historyStack.pop();
            parent[h.v] = h.v;
            if (h.rankIncreased) {
                rank[h.u]--;
            }
            k--;
        }
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
            int q = Integer.parseInt(st.nextToken());

            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
            historyStack.clear();

            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine());
                int type = Integer.parseInt(st.nextToken());
                if (type == 1) {
                    int u = Integer.parseInt(st.nextToken());
                    int v = Integer.parseInt(st.nextToken());
                    union(u, v);
                } else if (type == 2) {
                    int k = Integer.parseInt(st.nextToken());
                    rollback(k);
                } else {
                    int u = Integer.parseInt(st.nextToken());
                    int v = Integer.parseInt(st.nextToken());
                    if (find(u) == find(v)) {
                        ans.append('1');
                    } else {
                        ans.append('0');
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
