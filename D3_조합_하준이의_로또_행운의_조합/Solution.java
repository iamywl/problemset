import java.io.*;
import java.util.*;

public class Solution {
    static int k;
    static int[] s, picked;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            s = new int[k];
            for (int i = 0; i < k; i++) {
                s[i] = Integer.parseInt(st.nextToken());
            }

            picked = new int[6];
            sb.append("#").append(tc).append("\n");
            dfs(0, 0);
        }
        System.out.print(sb);
    }

    private static void dfs(int depth, int start) {
        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(picked[i]).append(i == 5 ? "" : " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < k; i++) {
            picked[depth] = s[i];
            dfs(depth + 1, i + 1);
        }
    }
}
