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

            long cur = 0;
            for (int i = 0; i < k; i++) cur += arr[i];

            long maxS = cur, minS = cur;

            for (int i = k; i < n; i++) {
                cur += arr[i] - arr[i - k];
                if (cur > maxS) maxS = cur;
                if (cur < minS) minS = cur;
            }

            sb.append("#").append(tc).append(" ").append(maxS - minS).append("\n");
        }
        System.out.print(sb);
    }
}
