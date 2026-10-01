import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static int[] scores;
    static long ways;
    static int maxScore;

    static void dfs(int curStep, int curScore) {
        if (curStep == n) {
            ways++;
            if (curScore > maxScore) maxScore = curScore;
            return;
        }
        if (curStep + 1 <= n) {
            dfs(curStep + 1, curScore + scores[curStep + 1]);
        }
        if (curStep + 2 <= n) {
            dfs(curStep + 2, curScore + scores[curStep + 2]);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            scores = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) scores[i] = Integer.parseInt(st.nextToken());

            ways = 0;
            maxScore = 0;
            dfs(0, 0);

            sb.append("#").append(tc).append(" ").append(ways).append(" ").append(maxScore).append("\n");
        }
        System.out.print(sb);
    }
}
