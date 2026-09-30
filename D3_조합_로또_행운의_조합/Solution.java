import java.io.*;
import java.util.*;

public class Solution {
    static int N, S;
    static int luckyWays;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());

            luckyWays = 0;
            comb(1, 0, 0, 0);

            sb.append("#").append(tc).append(" ").append(luckyWays).append("\n");
        }
        System.out.print(sb);
    }

    static void comb(int start, int depth, int oddCnt, int sum) {
        if (depth == 6) {
            if (oddCnt == 3 && sum >= S) luckyWays++;
            return;
        }

        for (int i = start; i <= N; i++) {
            comb(i + 1, depth + 1, oddCnt + (i % 2 != 0 ? 1 : 0), sum + i);
        }
    }
}
