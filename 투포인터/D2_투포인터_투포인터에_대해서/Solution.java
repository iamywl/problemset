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
            int target = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            int left = 0, right = n - 1;
            boolean found = false;
            int ansL = -1, ansR = -1;

            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum == target) {
                    ansL = arr[left];
                    ansR = arr[right];
                    found = true;
                    break;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }

            sb.append("#").append(tc).append(" ");
            if (found) {
                sb.append(ansL).append(" ").append(ansR).append("\n");
            } else {
                sb.append("-1\n");
            }
        }
        System.out.print(sb);
    }
}
