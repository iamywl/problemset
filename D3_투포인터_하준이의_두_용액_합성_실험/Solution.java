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
            int n = Integer.parseInt(br.readLine().trim());
            long[] arr = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Long.parseLong(st.nextToken());

            Arrays.sort(arr);

            int left = 0, right = n - 1;
            long bestDiff = Long.MAX_VALUE;
            long ansL = arr[left], ansR = arr[right];

            while (left < right) {
                long sum = arr[left] + arr[right];
                long absSum = Math.abs(sum);

                if (absSum < bestDiff) {
                    bestDiff = absSum;
                    ansL = arr[left];
                    ansR = arr[right];
                }

                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    break;
                }
            }

            sb.append("#").append(tc).append(" ").append(ansL).append(" ").append(ansR).append("\n");
        }
        System.out.print(sb);
    }
}
