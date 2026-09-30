import java.io.*;
import java.util.*;

public class Solution {
    static long[] h;

    static long getLargestArea(int left, int right) {
        if (left == right) return h[left];

        int mid = (left + right) >>> 1;
        long maxArea = Math.max(getLargestArea(left, mid), getLargestArea(mid + 1, right));

        // 중앙 경계를 걸치는 경우
        int lo = mid, hi = mid + 1;
        long minH = Math.min(h[lo], h[hi]);
        maxArea = Math.max(maxArea, minH * 2);

        while (left < lo || hi < right) {
            if (hi < right && (lo == left || h[lo - 1] < h[hi + 1])) {
                hi++;
                minH = Math.min(minH, h[hi]);
            } else {
                lo--;
                minH = Math.min(minH, h[lo]);
            }
            maxArea = Math.max(maxArea, minH * (hi - lo + 1));
        }

        return maxArea;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            h = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                h[i] = Long.parseLong(st.nextToken());
            }

            sb.append("#").append(tc).append(" ").append(getLargestArea(0, n - 1)).append("\n");
        }
        System.out.print(sb);
    }
}
