import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static char[] buf;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            buf = new char[2 * n];
            sb.append("#").append(tc).append("\n");
            dfs(0, 0, 0);
        }
        System.out.print(sb);
    }

    private static void dfs(int depth, int open, int close) {
        if (depth == 2 * n) {
            sb.append(new String(buf)).append("\n");
            return;
        }

        if (open < n) {
            buf[depth] = '(';
            dfs(depth + 1, open + 1, close);
        }

        if (close < open) {
            buf[depth] = ')';
            dfs(depth + 1, open, close + 1);
        }
    }
}
