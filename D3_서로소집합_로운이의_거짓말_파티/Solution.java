import java.io.*;
import java.util.*;

public class Solution {
    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
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
            int m = Integer.parseInt(st.nextToken());

            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;

            st = new StringTokenizer(br.readLine());
            int truthCount = Integer.parseInt(st.nextToken());
            int[] truth = new int[truthCount];
            for (int i = 0; i < truthCount; i++) {
                truth[i] = Integer.parseInt(st.nextToken());
            }

            int[][] parties = new int[m][];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int pSize = Integer.parseInt(st.nextToken());
                parties[i] = new int[pSize];
                for (int j = 0; j < pSize; j++) {
                    parties[i][j] = Integer.parseInt(st.nextToken());
                    if (j > 0) {
                        union(parties[i][0], parties[i][j]);
                    }
                }
            }

            // 진실을 아는 사람들의 루트 집합
            boolean[] truthRoots = new boolean[n + 1];
            for (int tPerson : truth) {
                truthRoots[find(tPerson)] = true;
            }

            int lieCount = 0;
            for (int i = 0; i < m; i++) {
                boolean canLie = true;
                for (int pPerson : parties[i]) {
                    if (truthRoots[find(pPerson)]) {
                        canLie = false;
                        break;
                    }
                }
                if (canLie) lieCount++;
            }

            sb.append("#").append(tc).append(" ").append(lieCount).append("\n");
        }
        System.out.print(sb);
    }
}
