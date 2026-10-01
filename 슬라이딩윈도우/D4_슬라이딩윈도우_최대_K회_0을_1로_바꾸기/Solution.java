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
            int k = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            int left = 0;
            int zeroCount = 0;
            int maxLen = 0;

            for (int right = 0; right < n; right++) {
                if (arr[right] == 0) zeroCount++;

                while (zeroCount > k) {
                    if (arr[left] == 0) zeroCount--;
                    left++;
                }

                int curLen = right - left + 1;
                if (curLen > maxLen) maxLen = curLen;
            }

            sb.append("#").append(tc).append(" ").append(maxLen).append("\n");
        }
        System.out.print(sb);
    }
}
