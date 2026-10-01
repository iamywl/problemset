import java.io.*;
import java.util.*;

public class Solution {
    static int n, s, count;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());

            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            count = 0;
            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int idx, int sum, int selected) {
        if (idx == n) {
            if (selected > 0 && sum == s) {
                count++;
            }
            return;
        }

        // Include
        dfs(idx + 1, sum + arr[idx], selected + 1);
        // Exclude
        dfs(idx + 1, sum, selected);
    }
}
