import java.io.*;
import java.util.*;

public class Solution {
    static long solve(int n, long r, long c) {
        if (n == 0) return 0;
        long half = 1L << (n - 1);
        long area = half * half;

        if (r < half && c < half) {
            // 좌상
            return solve(n - 1, r, c);
        } else if (r < half && c >= half) {
            // 우상
            return area + solve(n - 1, r, c - half);
        } else if (r >= half && c < half) {
            // 좌하
            return 2 * area + solve(n - 1, r - half, c);
        } else {
            // 우하
            return 3 * area + solve(n - 1, r - half, c - half);
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
            long r = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            sb.append("#").append(tc).append(" ").append(solve(n, r, c)).append("\n");
        }
        System.out.print(sb);
    }
}
