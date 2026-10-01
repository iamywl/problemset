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

            int[] temps = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) temps[i] = Integer.parseInt(st.nextToken());

            long windowSum = 0;
            for (int i = 0; i < k; i++) windowSum += temps[i];

            long maxSum = windowSum;
            for (int i = k; i < n; i++) {
                windowSum += temps[i] - temps[i - k];
                if (windowSum > maxSum) maxSum = windowSum;
            }

            sb.append("#").append(tc).append(" ").append(maxSum).append("\n");
        }
        System.out.print(sb);
    }
}
