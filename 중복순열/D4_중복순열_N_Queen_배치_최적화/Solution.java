import java.io.*;
import java.util.*;

public class Solution {
    static int n, count;
    static boolean[] col, diag1, diag2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());

            col = new boolean[n];
            diag1 = new boolean[2 * n];
            diag2 = new boolean[2 * n];
            count = 0;

            dfs(0);

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int r) {
        if (r == n) {
            count++;
            return;
        }

        for (int c = 0; c < n; c++) {
            if (!col[c] && !diag1[r - c + n] && !diag2[r + c]) {
                col[c] = true;
                diag1[r - c + n] = true;
                diag2[r + c] = true;

                dfs(r + 1);

                col[c] = false;
                diag1[r - c + n] = false;
                diag2[r + c] = false;
            }
        }
    }
}
