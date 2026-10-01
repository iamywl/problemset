import java.io.*;
import java.util.*;

public class Solution {
    static int upperBound(long[] arr, long target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

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
            long k = Long.parseLong(st.nextToken());

            long[] a = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) a[i] = Long.parseLong(st.nextToken());

            long[] b = new long[m];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) b[i] = Long.parseLong(st.nextToken());

            Arrays.sort(b);

            long low = 2; // 최소 원소합 1 + 1
            long high = 2000000000L; // 최대 원소합
            long ans = high;

            while (low <= high) {
                long mid = (low + high) >>> 1;
                long count = 0;
                for (int i = 0; i < n; i++) {
                    long rem = mid - a[i];
                    count += upperBound(b, rem);
                }

                if (count >= k) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
