import java.io.*;
import java.util.*;

public class Solution {
    static List<Long>[][] memo;
    static String expr;

    static List<Long> solve(int l, int r) {
        if (memo[l][r] != null) return memo[l][r];
        List<Long> res = new ArrayList<>();

        boolean hasOp = false;
        for (int i = l; i <= r; i++) {
            char ch = expr.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                hasOp = true;
                List<Long> left = solve(l, i - 1);
                List<Long> right = solve(i + 1, r);

                for (long a : left) {
                    for (long b : right) {
                        if (ch == '+') res.add(a + b);
                        else if (ch == '-') res.add(a - b);
                        else res.add(a * b);
                    }
                }
            }
        }
        if (!hasOp) {
            res.add(Long.parseLong(expr.substring(l, r + 1)));
        }

        return memo[l][r] = res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            expr = br.readLine().trim();
            int n = expr.length();
            memo = new ArrayList[n][n];

            List<Long> vals = solve(0, n - 1);
            long maxVal = Collections.max(vals);
            long minVal = Collections.min(vals);

            sb.append("#").append(tc).append(" ").append(maxVal).append(" ").append(minVal).append("\n");
        }
        System.out.print(sb);
    }
}
