import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[] P;
    static int[][] S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            P = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                P[i] = Integer.parseInt(st.nextToken());
            }

            S = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            long maxScore = Long.MIN_VALUE;

            // 2^N 비트마스크 순회
            int totalSubsets = 1 << N;
            for (int mask = 1; mask < totalSubsets; mask++) {
                if (Integer.bitCount(mask) < 2) continue;

                long score = 0;
                for (int i = 0; i < N; i++) {
                    if ((mask & (1 << i)) != 0) {
                        score += P[i];
                        for (int j = i + 1; j < N; j++) {
                            if ((mask & (1 << j)) != 0) {
                                score += S[i][j];
                            }
                        }
                    }
                }

                if (score > maxScore) {
                    maxScore = score;
                }
            }

            sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
        }
        System.out.print(sb);
    }
}
