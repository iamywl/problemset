import java.io.*;
import java.util.*;

public class Solution {
    static int K;
    static char[] op;
    static boolean[] used;
    static int[] pick;
    static String minStr, maxStr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            K = Integer.parseInt(br.readLine().trim());
            op = new char[K];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) op[i] = st.nextToken().charAt(0);

            used = new boolean[10];
            pick = new int[K + 1];
            minStr = null;
            maxStr = null;

            dfs(0);

            sb.append("#").append(tc).append(" ").append(maxStr).append(" ").append(minStr).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int depth) {
        if (depth == K + 1) {
            StringBuilder s = new StringBuilder();
            for (int x : pick) s.append(x);
            String str = s.toString();
            if (minStr == null) minStr = str;
            maxStr = str;
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (!used[i]) {
                if (depth > 0) {
                    if (op[depth - 1] == '<' && pick[depth - 1] >= i) continue;
                    if (op[depth - 1] == '>' && pick[depth - 1] <= i) continue;
                }
                used[i] = true;
                pick[depth] = i;
                dfs(depth + 1);
                used[i] = false;
            }
        }
    }
}
