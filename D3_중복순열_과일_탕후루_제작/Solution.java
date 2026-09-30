import java.io.*;
import java.util.*;

public class Solution {
    static int N, K;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            count = 0;
            dfs(0, -1, 0);

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int depth, int lastFruit, int runLen) {
        if (depth == K) {
            count++;
            return;
        }

        for (int fruit = 1; fruit <= N; fruit++) {
            if (fruit == lastFruit) {
                if (runLen < 2) {
                    dfs(depth + 1, fruit, runLen + 1);
                }
            } else {
                dfs(depth + 1, fruit, 1);
            }
        }
    }
}
