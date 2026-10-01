import java.io.*;
import java.util.*;

public class Solution {
    static long solveZ(int n, int r, int c) {
        if (n == 0) return 0;
        int half = 1 << (n - 1);
        long area = (long) half * half;

        if (r < half && c < half) {
            return solveZ(n - 1, r, c);
        } else if (r < half && c >= half) {
            return area + solveZ(n - 1, r, c - half);
        } else if (r >= half && c < half) {
            return 2 * area + solveZ(n - 1, r - half, c);
        } else {
            return 3 * area + solveZ(n - 1, r - half, c - half);
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
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            long ans = solveZ(n, r, c);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
