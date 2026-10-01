import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static int[][] s;
    static boolean[] team;
    static int minDiff;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            s = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    s[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            team = new boolean[n];
            minDiff = Integer.MAX_VALUE;

            // Fix 0 to start team to cut search space in half
            team[0] = true;
            dfs(1, 1);

            sb.append("#").append(tc).append(" ").append(minDiff).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int depth, int count) {
        if (count == n / 2) {
            calc();
            return;
        }
        if (depth == n) return;

        // Pick depth
        team[depth] = true;
        dfs(depth + 1, count + 1);

        // Don't pick depth
        team[depth] = false;
        dfs(depth + 1, count);
    }

    private static void calc() {
        int startScore = 0, linkScore = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (team[i] && team[j]) {
                    startScore += s[i][j] + s[j][i];
                } else if (!team[i] && !team[j]) {
                    linkScore += s[i][j] + s[j][i];
                }
            }
        }
        int diff = Math.abs(startScore - linkScore);
        if (diff < minDiff) minDiff = diff;
    }
}
