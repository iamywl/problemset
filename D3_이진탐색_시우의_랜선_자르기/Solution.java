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
            int k = Integer.parseInt(st.nextToken());
            long n = Long.parseLong(st.nextToken());
            
            long[] cables = new long[k];
            st = new StringTokenizer(br.readLine());
            long maxLen = 0;
            for (int i = 0; i < k; i++) {
                cables[i] = Long.parseLong(st.nextToken());
                if (cables[i] > maxLen) maxLen = cables[i];
            }
            
            long left = 1;
            long right = maxLen;
            long ans = 0;
            while (left <= right) {
                long mid = left + (right - left) / 2;
                long count = 0;
                for (int i = 0; i < k; i++) {
                    count += cables[i] / mid;
                }
                
                if (count >= n) {
                    ans = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
