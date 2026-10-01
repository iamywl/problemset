import java.io.*;
import java.util.*;

public class Solution {
    static long power(long c, long n, long m) {
        if (n == 0) return 1 % m;
        long half = power(c, n / 2, m);
        long res = (half * half) % m;
        if (n % 2 == 1) {
            res = (res * (c % m)) % m;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long c = Long.parseLong(st.nextToken());
            long n = Long.parseLong(st.nextToken());
            long m = Long.parseLong(st.nextToken());

            sb.append("#").append(tc).append(" ").append(power(c, n, m)).append("\n");
        }
        System.out.print(sb);
    }
}
