import java.io.*;
import java.util.*;

public class Solution {
    static int N, S;
    static int[] arr;
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
            S = Integer.parseInt(st.nextToken());

            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

            count = 0;
            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int idx, int sum, int chosenCount) {
        if (idx == N) {
            if (chosenCount > 0 && sum == S) count++;
            return;
        }

        // 선택
        dfs(idx + 1, sum + arr[idx], chosenCount + 1);
        // 미선택
        dfs(idx + 1, sum, chosenCount);
    }
}
