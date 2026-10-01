import java.io.*;
import java.util.*;

public class Solution {
    static int lowerBound(long[] arr, int len, long target) {
        int low = 0, high = len;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
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
            int n = Integer.parseInt(br.readLine().trim());
            long[] arr = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Long.parseLong(st.nextToken());

            long[] lis = new long[n];
            int len = 0;

            for (int i = 0; i < n; i++) {
                long x = arr[i];
                if (len == 0 || x > lis[len - 1]) {
                    lis[len++] = x;
                } else {
                    int pos = lowerBound(lis, len, x);
                    lis[pos] = x;
                }
            }

            sb.append("#").append(tc).append(" ").append(len).append("\n");
        }
        System.out.print(sb);
    }
}
