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
            int m = Integer.parseInt(st.nextToken());
            
            long[] gems = new long[m];
            st = new StringTokenizer(br.readLine());
            long maxGem = 0;
            for (int i = 0; i < m; i++) {
                gems[i] = Long.parseLong(st.nextToken());
                if (gems[i] > maxGem) maxGem = gems[i];
            }
            
            long left = 1;
            long right = maxGem;
            long ans = maxGem;
            
            while (left <= right) {
                long mid = left + (right - left) / 2;
                long neededPeople = 0;
                for (int i = 0; i < m; i++) {
                    neededPeople += (gems[i] + mid - 1) / mid;
                }
                
                if (neededPeople <= n) {
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
