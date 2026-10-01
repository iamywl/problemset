import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static char[] bits;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            bits = new char[n];
            sb.append("#").append(tc).append("\n");
            dfs(0);
        }
        System.out.print(sb);
    }

    private static void dfs(int depth) {
        if (depth == n) {
            sb.append(new String(bits)).append("\n");
            return;
        }

        bits[depth] = '0';
        dfs(depth + 1);

        bits[depth] = '1';
        dfs(depth + 1);
    }
}
