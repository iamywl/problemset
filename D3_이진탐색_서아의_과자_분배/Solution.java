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

            long[] sticks = new long[n];
            long maxLen = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                sticks[i] = Long.parseLong(st.nextToken());
                if (sticks[i] > maxLen) maxLen = sticks[i];
            }

            long low = 1, high = maxLen;
            long ans = 0;
            while (low <= high) {
                long mid = (low + high) >>> 1;
                long count = 0;
                for (long s : sticks) {
                    count += (s / mid);
                }

                if (count >= m) {
                    ans = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
