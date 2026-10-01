import java.io.*;
import java.util.*;

public class Solution {
    static final long MOD = 1000000007L;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            long n = Long.parseLong(br.readLine().trim());
            sb.append("#").append(tc).append(" ").append(fib(n)).append("\n");
        }
        System.out.print(sb);
    }
    
    private static long fib(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        long[][] res = {{1, 0}, {0, 1}};
        long[][] base = {{1, 1}, {1, 0}};
        long exp = n - 1;
        
        while (exp > 0) {
            if (exp % 2 == 1) {
                res = multiply(res, base);
            }
            base = multiply(base, base);
            exp /= 2;
        }
        return res[0][0];
    }
    
    private static long[][] multiply(long[][] a, long[][] b) {
        long[][] c = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < 2; j++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }
}
