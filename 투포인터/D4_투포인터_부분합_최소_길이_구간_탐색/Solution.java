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
            long s = Long.parseLong(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            int left = 0;
            long curSum = 0;
            int minLen = Integer.MAX_VALUE;

            for (int right = 0; right < n; right++) {
                curSum += arr[right];
                while (curSum >= s) {
                    minLen = Math.min(minLen, right - left + 1);
                    curSum -= arr[left];
                    left++;
                }
            }

            int ans = (minLen == Integer.MAX_VALUE) ? 0 : minLen;
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
