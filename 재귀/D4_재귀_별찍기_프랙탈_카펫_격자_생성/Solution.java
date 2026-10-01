import java.io.*;
import java.util.*;

public class Solution {
    static long countRowStars(int k, long r) {
        if (k == 0) return 1;
        long p = 1;
        for (int i = 0; i < k - 1; i++) p *= 3;

        long block = r / p;
        long rem = r % p;

        if (block == 1) {
            // 가운데 블록: 좌우 2개 블록만 별
            return 2 * countRowStars(k - 1, rem);
        } else {
            // 위 또는 아래 블록: 3개 블록 모두 별
            return 3 * countRowStars(k - 1, rem);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            long r = Long.parseLong(st.nextToken());

            long totalStars = 1;
            for (int i = 0; i < k; i++) totalStars *= 8;

            long rowStars = countRowStars(k, r);

            sb.append("#").append(tc).append(" ").append(totalStars).append(" ").append(rowStars).append("\n");
        }
        System.out.print(sb);
    }
}
