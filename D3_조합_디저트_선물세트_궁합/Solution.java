import java.io.*;
import java.util.*;

public class Solution {
    static int N, R, M;
    static boolean[][] conflict;
    static int[] chosen;
    static int validCount;

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
            M = Integer.parseInt(st.nextToken());

            conflict = new boolean[N + 1][N + 1];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                conflict[u][v] = conflict[v][u] = true;
            }

            chosen = new int[R];
            validCount = 0;
            backtrack(1, 0);

            sb.append("#").append(tc).append(" ").append(validCount).append("\n");
        }
        System.out.print(sb);
    }

    static void backtrack(int start, int depth) {
        if (depth == R) {
            validCount++;
            return;
        }

        for (int i = start; i <= N; i++) {
            // 가지치기: 이미 선택된 과일과 상극인지 확인
            boolean ok = true;
            for (int d = 0; d < depth; d++) {
                if (conflict[chosen[d]][i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                chosen[depth] = i;
                backtrack(i + 1, depth + 1);
            }
        }
    }
}
