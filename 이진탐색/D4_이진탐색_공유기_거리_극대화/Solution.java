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
            int c = Integer.parseInt(st.nextToken());

            int[] coords = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                coords[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(coords);

            int low = 1, high = coords[n - 1] - coords[0];
            int ans = 1;

            while (low <= high) {
                int mid = (low + high) >>> 1;
                // 첫 번째 집은 필수 설치
                int count = 1;
                int last = coords[0];

                for (int i = 1; i < n; i++) {
                    if (coords[i] - last >= mid) {
                        count++;
                        last = coords[i];
                    }
                }

                if (count >= c) {
                    ans = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
