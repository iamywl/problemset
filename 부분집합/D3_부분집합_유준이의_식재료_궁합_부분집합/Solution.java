import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static long[] sArr, bArr;
    static long minDiff;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            sArr = new long[n];
            bArr = new long[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                sArr[i] = Long.parseLong(st.nextToken());
                bArr[i] = Long.parseLong(st.nextToken());
            }

            minDiff = Long.MAX_VALUE;
            dfs(0, 1, 0, 0);

            sb.append("#").append(tc).append(" ").append(minDiff).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int idx, long prodS, long sumB, int count) {
        if (idx == n) {
            if (count > 0) {
                long diff = Math.abs(prodS - sumB);
                if (diff < minDiff) minDiff = diff;
            }
            return;
        }

        // Include
        dfs(idx + 1, prodS * sArr[idx], sumB + bArr[idx], count + 1);
        // Exclude
        dfs(idx + 1, prodS, sumB, count);
    }
}
