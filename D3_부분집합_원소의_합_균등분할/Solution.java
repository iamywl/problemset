import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[] arr;
    static int target;
    static boolean possible;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int total = 0;
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                total += arr[i];
            }

            possible = false;
            if (total % 2 == 0) {
                target = total / 2;
                dfs(0, 0);
            }

            sb.append("#").append(tc).append(" ").append(possible ? 1 : 0).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int idx, int sum) {
        if (possible) return;
        if (sum == target) {
            possible = true;
            return;
        }
        if (sum > target || idx == N) return;

        dfs(idx + 1, sum + arr[idx]);
        dfs(idx + 1, sum);
    }
}
