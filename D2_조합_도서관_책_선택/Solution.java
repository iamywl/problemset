import java.io.*;
import java.util.*;

public class Solution {
    static int N, R, P;
    static int[] pages;
    static int validWays;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            pages = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) pages[i] = Integer.parseInt(st.nextToken());

            validWays = 0;
            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(validWays).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int start, int depth, int sum) {
        if (sum > P) return;
        if (depth == R) {
            validWays++;
            return;
        }

        for (int i = start; i < N; i++) {
            dfs(i + 1, depth + 1, sum + pages[i]);
        }
    }
}
