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
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(br.readLine().trim());

            Arrays.sort(arr);

            int left = 0, right = n - 1;
            int count = 0;

            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum == x) {
                    count++;
                    left++;
                    right--;
                } else if (sum < x) {
                    left++;
                } else {
                    right--;
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
}
