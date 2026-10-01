import java.io.*;
import java.util.*;

public class Solution {
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
            
            long result = power(c, n, m);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }
    
    private static long power(long base, long exp, long mod) {
        if (exp == 0) return 1 % mod;
        if (exp == 1) return base % mod;
        
        long half = power(base, exp / 2, mod);
        long res = (half * half) % mod;
        if (exp % 2 == 1) {
            res = (res * (base % mod)) % mod;
        }
        return res;
    }
}
