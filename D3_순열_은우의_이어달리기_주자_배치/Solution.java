import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static int[][] s;
    static int[] perm;
    static boolean[] visited;
    static int maxSynergy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            s = new int[n][n];
            perm = new int[n];
            visited = new boolean[n];
            maxSynergy = 0;

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    s[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0);
            sb.append("#").append(tc).append(" ").append(maxSynergy).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int depth) {
        if (depth == n) {
            int score = 0;
            for (int i = 0; i < n - 1; i++) {
                score += s[perm[i]][perm[i + 1]];
            }
            if (score > maxSynergy) maxSynergy = score;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm[depth] = i;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}
