import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] S;
    static boolean[] team;
    static int minDiff;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            S = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) S[i][j] = Integer.parseInt(st.nextToken());
            }

            team = new boolean[N];
            minDiff = Integer.MAX_VALUE;

            // 0번 선수는 항상 스타트 팀에 고정하여 중복 제거
            team[0] = true;
            comb(1, 1);

            sb.append("#").append(tc).append(" ").append(minDiff).append("\n");
        }
        System.out.print(sb);
    }

    static void comb(int start, int depth) {
        if (depth == N / 2) {
            int startScore = 0, linkScore = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (team[i] && team[j]) startScore += S[i][j] + S[j][i];
                    else if (!team[i] && !team[j]) linkScore += S[i][j] + S[j][i];
                }
            }
            int diff = Math.abs(startScore - linkScore);
            if (diff < minDiff) minDiff = diff;
            return;
        }

        for (int i = start; i < N; i++) {
            team[i] = true;
            comb(i + 1, depth + 1);
            team[i] = false;
        }
    }
}
