import java.io.*;
import java.util.*;

public class Solution {
    static int n, r;
    static int[] picked;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            picked = new int[r];
            sb.append("#").append(tc).append("\n");
            dfs(0, 1);
        }
        System.out.print(sb);
    }

    private static void dfs(int depth, int start) {
        if (depth == r) {
            for (int i = 0; i < r; i++) {
                sb.append(picked[i]).append(i == r - 1 ? "" : " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= n; i++) {
            picked[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }
}
