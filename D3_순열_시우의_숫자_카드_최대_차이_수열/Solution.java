import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static int[] arr, perm;
    static boolean[] visited;
    static int maxDiff;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            arr = new int[n];
            perm = new int[n];
            visited = new boolean[n];
            maxDiff = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0);
            sb.append("#").append(tc).append(" ").append(maxDiff).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int depth) {
        if (depth == n) {
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += Math.abs(perm[i] - perm[i + 1]);
            }
            if (sum > maxDiff) maxDiff = sum;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm[depth] = arr[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}
