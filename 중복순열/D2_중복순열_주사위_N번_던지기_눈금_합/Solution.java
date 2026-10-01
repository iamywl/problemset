import java.io.*;
import java.util.*;

public class Solution {
    static int n, s, count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());

            count = 0;
            dfs(0, 0);

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int depth, int sum) {
        if (depth == n) {
            if (sum == s) count++;
            return;
        }

        for (int dice = 1; dice <= 6; dice++) {
            dfs(depth + 1, sum + dice);
        }
    }
}
