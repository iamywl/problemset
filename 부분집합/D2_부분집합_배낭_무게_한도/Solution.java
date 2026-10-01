import java.io.*;
import java.util.*;

public class Solution {
    static int N, K;
    static int[] W, V;
    static int maxVal;

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

            W = new int[N];
            V = new int[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                W[i] = Integer.parseInt(st.nextToken());
                V[i] = Integer.parseInt(st.nextToken());
            }

            maxVal = 0;
            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(maxVal).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int idx, int curW, int curV) {
        if (curW > K) return;
        if (idx == N) {
            if (curV > maxVal) maxVal = curV;
            return;
        }

        dfs(idx + 1, curW + W[idx], curV + V[idx]);
        dfs(idx + 1, curW, curV);
    }
}
