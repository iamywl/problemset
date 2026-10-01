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

            long winSum = 0;
            for (int i = 0; i < k; i++) winSum += arr[i];

            StringBuilder out = new StringBuilder();
            out.append(winSum);
            long maxSum = winSum;

            for (int i = k; i < n; i++) {
                winSum += arr[i] - arr[i - k];
                out.append(" ").append(winSum);
                if (winSum > maxSum) maxSum = winSum;
            }

            out.append(" ").append(maxSum);

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
