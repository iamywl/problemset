import java.io.*;
import java.util.*;

public class Solution {
    static int k;
    static char[] op;
    static boolean[] visited;
    static int[] ans;
    static String minStr, maxStr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            k = Integer.parseInt(br.readLine().trim());
            op = new char[k];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                op[i] = st.nextToken().charAt(0);
            }

            ans = new int[k + 1];
            visited = new boolean[10];
            maxStr = null;
            minStr = null;

            dfsMax(0);
            Arrays.fill(visited, false);
            dfsMin(0);

            sb.append("#").append(tc).append("\n").append(maxStr).append("\n").append(minStr).append("\n");
        }
        System.out.print(sb);
    }

    private static boolean dfsMax(int depth) {
        if (depth == k + 1) {
            StringBuilder s = new StringBuilder();
            for (int x : ans) s.append(x);
            maxStr = s.toString();
            return true;
        }

        for (int i = 9; i >= 0; i--) {
            if (!visited[i]) {
                if (depth > 0) {
                    if (op[depth - 1] == '<' && ans[depth - 1] >= i) continue;
                    if (op[depth - 1] == '>' && ans[depth - 1] <= i) continue;
                }
                visited[i] = true;
                ans[depth] = i;
                if (dfsMax(depth + 1)) return true;
                visited[i] = false;
            }
        }
        return false;
    }

    private static boolean dfsMin(int depth) {
        if (depth == k + 1) {
            StringBuilder s = new StringBuilder();
            for (int x : ans) s.append(x);
            minStr = s.toString();
            return true;
        }

        for (int i = 0; i <= 9; i++) {
            if (!visited[i]) {
                if (depth > 0) {
                    if (op[depth - 1] == '<' && ans[depth - 1] >= i) continue;
                    if (op[depth - 1] == '>' && ans[depth - 1] <= i) continue;
                }
                visited[i] = true;
                ans[depth] = i;
                if (dfsMin(depth + 1)) return true;
                visited[i] = false;
            }
        }
        return false;
    }
}
