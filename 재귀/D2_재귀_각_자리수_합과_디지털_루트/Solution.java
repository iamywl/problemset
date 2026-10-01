import java.io.*;
import java.util.*;

public class Solution {
    static long digitSum(long n) {
        if (n < 10) return n;
        return (n % 10) + digitSum(n / 10);
    }

    static long[] getDigitalRoot(long n, long steps) {
        if (n < 10) return new long[]{n, steps};
        long nextVal = digitSum(n);
        return getDigitalRoot(nextVal, steps + 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            long n = Long.parseLong(br.readLine().trim());
            long[] res = getDigitalRoot(n, 0);
            sb.append("#").append(tc).append(" ").append(res[0]).append(" ").append(res[1]).append("\n");
        }
        System.out.print(sb);
    }
}
