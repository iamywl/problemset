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
            long target = Long.parseLong(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            long sum = 0;
            for (int i = 0; i < k; i++) sum += arr[i];

            int validCount = (sum >= target) ? 1 : 0;

            for (int i = k; i < n; i++) {
                sum += arr[i] - arr[i - k];
                if (sum >= target) validCount++;
            }

            sb.append("#").append(tc).append(" ").append(validCount).append("\n");
        }
        System.out.print(sb);
    }
}
