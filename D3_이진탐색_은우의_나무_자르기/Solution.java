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
            
            long[] trees = new long[n];
            st = new StringTokenizer(br.readLine());
            long maxH = 0;
            for (int i = 0; i < n; i++) {
                trees[i] = Long.parseLong(st.nextToken());
                if (trees[i] > maxH) maxH = trees[i];
            }
            
            long left = 0;
            long right = maxH;
            long ans = 0;
            while (left <= right) {
                long mid = left + (right - left) / 2;
                long totalWood = 0;
                for (int i = 0; i < n; i++) {
                    if (trees[i] > mid) {
                        totalWood += (trees[i] - mid);
                    }
                }
                
                if (totalWood >= m) {
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
