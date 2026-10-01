import java.io.*;
import java.util.*;

public class Solution {
    static int N, K;
    static boolean[][] broken;
    static long[][][] dp;

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

            broken = new boolean[2][N];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                broken[r][c] = true;
            }

            int validCells = 2 * N - K;
            long ans = 0;
            if (validCells % 2 == 0) {
                dp = new long[N + 1][4][4];
                // 열 단위 비트마스크 상태 전이
                // mask: 00(둘다비어있음), 01(0행채움), 10(1행채움), 11(둘다채움)
                ans = solve();
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    static long solve() {
        long[] prev = new long[4];
        prev[0] = 1; // 0열 진입 시 아무것도 튀어나오지 않음

        for (int c = 0; c < N; c++) {
            long[] next = new long[4];
            int bMask = (broken[0][c] ? 1 : 0) | (broken[1][c] ? 2 : 0);

            for (int inMask = 0; inMask < 4; inMask++) {
                if (prev[inMask] == 0) continue;
                // 이전 열에서 튀어나온 블록과 파손된 칸이 겹치면 불가능
                if ((inMask & bMask) != 0) continue;

                int curOccupied = inMask | bMask;
                long count = prev[inMask];

                if (curOccupied == 3) {
                    // 이미 둘 다 채워짐 -> 다음 열로 튀어나가지 않음(0)
                    next[0] += count;
                } else if (curOccupied == 0) {
                    // 둘 다 비어있음
                    // 1) 둘 다 다음 열로 가로 배치 (next mask 3)
                    next[3] += count;
                    // 2) 현재 열에서 세로 1개 배치 (next mask 0)
                    next[0] += count;
                } else if (curOccupied == 1) {
                    // 0행은 채워짐, 1행만 비어있음 -> 1행 가로 배치 (next mask 2)
                    next[2] += count;
                } else if (curOccupied == 2) {
                    // 1행 채워짐, 0행 비어있음 -> 0행 가로 배치 (next mask 1)
                    next[1] += count;
                }
            }
            prev = next;
        }

        return prev[0]; // 마지막 열 이후 튀어나온 블록 없어야 함
    }
}
