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
            int n = Integer.parseInt(st.nextToken());
            long m = Long.parseLong(st.nextToken());
            
            long[] times = new long[n];
            st = new StringTokenizer(br.readLine());
            long minTime = Long.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                times[i] = Long.parseLong(st.nextToken());
                if (times[i] < minTime) minTime = times[i];
            }
            
            long left = 1;
            long right = minTime * m;
            long ans = right;
            
            while (left <= right) {
                long mid = left + (right - left) / 2;
                long totalServed = 0;
                for (int i = 0; i < n; i++) {
                    totalServed += (mid / times[i]);
                    if (totalServed >= m) break; // prevent long overflow
                }
                
                if (totalServed >= m) {
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
