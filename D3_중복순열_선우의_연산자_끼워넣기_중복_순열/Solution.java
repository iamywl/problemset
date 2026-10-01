import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static int[] a;
    static long maxVal, minVal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            a = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            maxVal = Long.MIN_VALUE;
            minVal = Long.MAX_VALUE;

            dfs(1, a[0]);

            sb.append("#").append(tc).append(" ").append(maxVal).append(" ").append(minVal).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int idx, long cur) {
        if (idx == n) {
            if (cur > maxVal) maxVal = cur;
            if (cur < minVal) minVal = cur;
            return;
        }

        // '+'
        dfs(idx + 1, cur + a[idx]);
        // '*'
        dfs(idx + 1, cur * a[idx]);
    }
}
