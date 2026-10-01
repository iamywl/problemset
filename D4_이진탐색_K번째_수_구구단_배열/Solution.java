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
            long n = Long.parseLong(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            
            long left = 1;
            long right = Math.min((long)1e9, n * n);
            long ans = right;
            
            while (left <= right) {
                long mid = left + (right - left) / 2;
                long count = 0;
                for (long i = 1; i <= n; i++) {
                    count += Math.min(n, mid / i);
                }
                
                if (count >= k) {
                    ans = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
