import java.io.*;
import java.util.*;

public class Solution {
    static int n, r, count;
    static int[] counts;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            counts = new int[n + 1];
            count = 0;
            dfs(0, 1);

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int depth, int start) {
        if (depth == r) {
            count++;
            return;
        }

        for (int i = start; i <= n; i++) {
            if (counts[i] < 2) {
                counts[i]++;
                dfs(depth + 1, i);
                counts[i]--;
            }
        }
    }
}
