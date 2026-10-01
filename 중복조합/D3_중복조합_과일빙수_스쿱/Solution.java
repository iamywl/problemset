import java.io.*;
import java.util.*;

public class Solution {
    static int N, K;
    static int[] minS, maxS;
    static int ways;

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

            minS = new int[N];
            maxS = new int[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                minS[i] = Integer.parseInt(st.nextToken());
                maxS[i] = Integer.parseInt(st.nextToken());
            }

            ways = 0;
            solve(0, 0);

            sb.append("#").append(tc).append(" ").append(ways).append("\n");
        }
        System.out.print(sb);
    }

    static void solve(int idx, int currentTotal) {
        if (idx == N) {
            if (currentTotal == K) ways++;
            return;
        }

        // 남은 과일들이 채울 수 있는 최대치보다 적게 남아있으면 불가능
        int remainingMax = 0;
        for (int i = idx; i < N; i++) remainingMax += maxS[i];
        if (currentTotal + remainingMax < K) return;

        for (int scoop = minS[idx]; scoop <= maxS[idx]; scoop++) {
            if (currentTotal + scoop <= K) {
                solve(idx + 1, currentTotal + scoop);
            }
        }
    }
}
