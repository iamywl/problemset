import java.io.*;
import java.util.*;

public class Solution {
    static int M;
    static final int MOD = 1000;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            long B = Long.parseLong(st.nextToken());
            
            long[][] A = new long[M][M];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    A[i][j] = Long.parseLong(st.nextToken()) % MOD;
                }
            }
            
            long[][] result = power(A, B);
            sb.append("#").append(tc).append("\n");
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    sb.append(result[i][j]).append(j == M - 1 ? "" : " ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
    
    private static long[][] power(long[][] a, long b) {
        long[][] res = new long[M][M];
        for (int i = 0; i < M; i++) res[i][i] = 1;
        long[][] base = a;
        
        while (b > 0) {
            if (b % 2 == 1) {
                res = multiply(res, base);
            }
            base = multiply(base, base);
            b /= 2;
        }
        return res;
    }
    
    private static long[][] multiply(long[][] a, long[][] b) {
        long[][] c = new long[M][M];
        for (int i = 0; i < M; i++) {
            for (int k = 0; k < M; k++) {
                for (int j = 0; j < M; j++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }
}
