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
            int n = Integer.parseInt(br.readLine().trim());
            long[] req = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            long maxReq = 0;
            long sumReq = 0;
            for (int i = 0; i < n; i++) {
                req[i] = Long.parseLong(st.nextToken());
                if (req[i] > maxReq) maxReq = req[i];
                sumReq += req[i];
            }
            long m = Long.parseLong(br.readLine().trim());
            
            if (sumReq <= m) {
                sb.append("#").append(tc).append(" ").append(maxReq).append("\n");
                continue;
            }
            
            long left = 1;
            long right = maxReq;
            long ans = 1;
            while (left <= right) {
                long mid = left + (right - left) / 2;
                long total = 0;
                for (int i = 0; i < n; i++) {
                    total += Math.min(req[i], mid);
                }
                
                if (total <= m) {
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
