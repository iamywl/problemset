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
            int k = Integer.parseInt(st.nextToken());

            long[] coords = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                coords[i] = Long.parseLong(st.nextToken());
            }

            if (k >= n) {
                sb.append("#").append(tc).append(" 0\n");
                continue;
            }

            Arrays.sort(coords);

            long[] diff = new long[n - 1];
            for (int i = 0; i < n - 1; i++) {
                diff[i] = coords[i + 1] - coords[i];
            }

            Arrays.sort(diff);

            long ans = 0;
            // 가장 큰 (k - 1)개를 제외한 (n - 1) - (k - 1) = n - k 개의 작은 간격 합산
            for (int i = 0; i < n - k; i++) {
                ans += diff[i];
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
