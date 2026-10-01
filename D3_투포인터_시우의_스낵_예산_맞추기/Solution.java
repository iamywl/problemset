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
            int m = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            Arrays.sort(arr);

            int left = 0, right = n - 1;
            int maxSum = -1;

            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum <= m) {
                    if (sum > maxSum) maxSum = sum;
                    left++;
                } else {
                    right--;
                }
            }

            sb.append("#").append(tc).append(" ").append(maxSum).append("\n");
        }
        System.out.print(sb);
    }
}
