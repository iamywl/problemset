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

            long bestDiff = Long.MAX_VALUE;
            long a1 = 0, a2 = 0, a3 = 0;

            for (int i = 0; i < n - 2; i++) {
                int left = i + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = arr[i] + arr[left] + arr[right];
                    long abs = Math.abs(sum);

                    if (abs < bestDiff) {
                        bestDiff = abs;
                        a1 = arr[i];
                        a2 = arr[left];
                        a3 = arr[right];
                    }

                    if (sum < 0) left++;
                    else if (sum > 0) right--;
                    else break;
                }
                if (bestDiff == 0) break;
            }

            sb.append("#").append(tc).append(" ").append(a1).append(" ").append(a2).append(" ").append(a3).append("\n");
        }
        System.out.print(sb);
    }
}
