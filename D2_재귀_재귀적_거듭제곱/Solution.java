import java.io.*;
import java.util.*;

public class Solution {
    static final long MOD = 1000000007L;

    static long power(long c, int n) {
        if (n == 0) return 1;
        long half = power(c, n / 2);
        long res = (half * half) % MOD;
        if (n % 2 == 1) res = (res * (c % MOD)) % MOD;
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
            int n = Integer.parseInt(st.nextToken());

            long ans = power(c, n);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
