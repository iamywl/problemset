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
            long m = Long.parseLong(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            long curSum = 0;
            for (int i = 0; i < k; i++) curSum += arr[i];

            long bestSum = (curSum <= m) ? curSum : -1;
            int count = (curSum <= m) ? 1 : 0;

            for (int i = 1; i < n; i++) {
                curSum += arr[(i + k - 1) % n] - arr[i - 1];
                if (curSum <= m) {
                    if (curSum > bestSum) {
                        bestSum = curSum;
                        count = 1;
                    } else if (curSum == bestSum) {
                        count++;
                    }
                }
            }

            if (bestSum == -1) count = 0;
            sb.append("#").append(tc).append(" ").append(bestSum).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
}
