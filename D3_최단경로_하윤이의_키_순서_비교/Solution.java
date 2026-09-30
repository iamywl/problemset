import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            boolean[][] reach = new boolean[n + 1][n + 1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                reach[a][b] = true;
            }

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    if (!reach[i][k]) continue;
                    for (int j = 1; j <= n; j++) {
                        if (reach[i][k] && reach[k][j]) {
                            reach[i][j] = true;
                        }
                    }
                }
            }

            int knownRankCount = 0;
            for (int i = 1; i <= n; i++) {
                int count = 0;
                for (int j = 1; j <= n; j++) {
                    if (i != j && (reach[i][j] || reach[j][i])) {
                        count++;
                    }
                }
                if (count == n - 1) {
                    knownRankCount++;
                }
            }

            sb.append("#").append(tc).append(" ").append(knownRankCount).append("\n");
        }
        System.out.print(sb);
    }
}
