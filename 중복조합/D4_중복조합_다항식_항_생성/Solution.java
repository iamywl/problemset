import java.io.*;
import java.util.*;

public class Solution {
    static int N, K;
    static long M;
    static int validTerms;
    static long[] fact;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        fact = new long[15];
        fact[0] = 1;
        for (int i = 1; i <= 14; i++) fact[i] = fact[i - 1] * i;

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            M = Long.parseLong(st.nextToken());

            validTerms = 0;
            dfs(0, 0, fact[K]);

            sb.append("#").append(tc).append(" ").append(validTerms).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int idx, int used, long curCoeff) {
        if (idx == N - 1) {
            int rem = K - used;
            long finalCoeff = curCoeff / fact[rem];
            if (finalCoeff >= M) validTerms++;
            return;
        }

        for (int e = 0; used + e <= K; e++) {
            dfs(idx + 1, used + e, curCoeff / fact[e]);
        }
    }
}
