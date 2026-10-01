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

            int[] lis = new int[n];
            int len = 0;

            for (int i = 0; i < n; i++) {
                int x = a[i];
                int idx = binarySearch(lis, 0, len, x);
                lis[idx] = x;
                if (idx == len) {
                    len++;
                }
            }

            sb.append("#").append(tc).append(" ").append(len).append("\n");
        }
        System.out.print(sb);
    }

    private static int binarySearch(int[] lis, int start, int end, int target) {
        int l = start, r = end;
        while (l < r) {
            int mid = (l + r) / 2;
            if (lis[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
