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
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            int i = n - 1;
            while (i > 0 && a[i - 1] >= a[i]) {
                i--;
            }

            sb.append("#").append(tc).append(" ");
            if (i <= 0) {
                sb.append("-1\n");
            } else {
                int j = n - 1;
                while (a[j] <= a[i - 1]) {
                    j--;
                }
                int temp = a[i - 1];
                a[i - 1] = a[j];
                a[j] = temp;

                int left = i, right = n - 1;
                while (left < right) {
                    temp = a[left];
                    a[left] = a[right];
                    a[right] = temp;
                    left++;
                    right--;
                }

                for (int k = 0; k < n; k++) {
                    sb.append(a[k]).append(k == n - 1 ? "" : " ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
