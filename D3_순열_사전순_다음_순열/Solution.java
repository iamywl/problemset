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

            sb.append("#").append(tc).append(" ");
            if (nextPermutation(arr)) {
                for (int i = 0; i < n; i++) {
                    sb.append(arr[i]).append(i == n - 1 ? "" : " ");
                }
            } else {
                sb.append("-1");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static boolean nextPermutation(int[] a) {
        int i = a.length - 2;
        while (i >= 0 && a[i] >= a[i + 1]) i--;
        if (i < 0) return false;

        int j = a.length - 1;
        while (a[j] <= a[i]) j--;

        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;

        int l = i + 1, r = a.length - 1;
        while (l < r) {
            tmp = a[l]; a[l] = a[r]; a[r] = tmp;
            l++; r--;
        }
        return true;
    }
}
