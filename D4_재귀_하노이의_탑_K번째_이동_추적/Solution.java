import java.io.*;
import java.util.*;

public class Solution {
    static long[] res; // [disk, from, to]

    static void findKth(int n, long k, int from, int by, int to) {
        long half = 1L << (n - 1);
        if (k == half) {
            res = new long[]{n, from, to};
            return;
        }
        if (k < half) {
            findKth(n - 1, k, from, to, by);
        } else {
            findKth(n - 1, k - half, by, from, to);
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
            long k = Long.parseLong(st.nextToken());

            findKth(n, k, 1, 2, 3);
            sb.append("#").append(tc).append(" ").append(res[0]).append(" ").append(res[1]).append(" ").append(res[2]).append("\n");
        }
        System.out.print(sb);
    }
}
