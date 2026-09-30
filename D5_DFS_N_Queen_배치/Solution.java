import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static int N;
    static int ans;
    static boolean[] colUsed;
    static boolean[] diag1; // r + c
    static boolean[] diag2; // r - c + N - 1

    static void dfs(int row) {
        if (row == N) {
            ans++;
            return;
        }

        for (int c = 0; c < N; c++) {
            int d1 = row + c;
            int d2 = row - c + N - 1;
            if (!colUsed[c] && !diag1[d1] && !diag2[d2]) {
                colUsed[c] = diag1[d1] = diag2[d2] = true;
                dfs(row + 1);
                colUsed[c] = diag1[d1] = diag2[d2] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            ans = 0;
            colUsed = new boolean[N];
            diag1 = new boolean[2 * N];
            diag2 = new boolean[2 * N];

            dfs(0);
            System.out.println("#" + tc + " " + ans);
        }
    }
}
