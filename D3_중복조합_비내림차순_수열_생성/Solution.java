import java.io.*;
import java.util.*;

public class Solution {
    static int N, K;
    static int evenSumCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            evenSumCount = 0;
            comb(1, 0, 0);

            sb.append("#").append(tc).append(" ").append(evenSumCount).append("\n");
        }
        System.out.print(sb);
    }

    static void comb(int start, int depth, int sum) {
        if (depth == K) {
            if (sum % 2 == 0) evenSumCount++;
            return;
        }

        for (int i = start; i <= N; i++) {
            comb(i, depth + 1, sum + i);
        }
    }
}
