import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static long s;
    static long[] a;
    static List<Long> leftSums, rightSums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            s = Long.parseLong(st.nextToken());

            a = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
            }

            leftSums = new ArrayList<>();
            rightSums = new ArrayList<>();

            getSums(0, n / 2, 0, leftSums);
            getSums(n / 2, n, 0, rightSums);

            Collections.sort(rightSums);

            long ans = 0;
            for (long x : leftSums) {
                long target = s - x;
                ans += upperBound(rightSums, target) - lowerBound(rightSums, target);
            }

            if (s == 0) ans--; // exclude empty subset

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    private static void getSums(int idx, int end, long sum, List<Long> list) {
        if (idx == end) {
            list.add(sum);
            return;
        }
        getSums(idx + 1, end, sum + a[idx], list);
        getSums(idx + 1, end, sum, list);
    }

    private static int lowerBound(List<Long> list, long target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private static int upperBound(List<Long> list, long target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
