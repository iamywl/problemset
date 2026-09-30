import java.io.*;
import java.util.*;

public class Solution {
    static int N, K;
    static int[] A, B;
    static long maxEffect;

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

            A = new int[N];
            B = new int[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                A[i] = Integer.parseInt(st.nextToken());
                B[i] = Integer.parseInt(st.nextToken());
            }

            maxEffect = Long.MIN_VALUE;
            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(maxEffect).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int idx, int used, long curEffect) {
        if (idx == N - 1) {
            int rem = K - used;
            long finalEff = curEffect + (long) A[idx] * rem - (long) B[idx] * rem * rem;
            if (finalEff > maxEffect) maxEffect = finalEff;
            return;
        }

        for (int x = 0; used + x <= K; x++) {
            long eff = (long) A[idx] * x - (long) B[idx] * x * x;
            dfs(idx + 1, used + x, curEffect + eff);
        }
    }
}
