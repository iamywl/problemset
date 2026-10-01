import java.io.*;
import java.util.*;

public class Solution {
    static int N, L;
    static int[] cal, val;
    static int maxVal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            cal = new int[N];
            val = new int[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                cal[i] = Integer.parseInt(st.nextToken());
                val[i] = Integer.parseInt(st.nextToken());
            }

            maxVal = 0;
            dfs(0, 0, 0, 0);

            sb.append("#").append(tc).append(" ").append(maxVal).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int idx, int curCal, int curVal, int count) {
        if (curCal > L) return;
        if (idx == N) {
            if (count > 0 && curVal > maxVal) {
                maxVal = curVal;
            }
            return;
        }

        // 선택함
        dfs(idx + 1, curCal + cal[idx], curVal + val[idx], count + 1);
        // 선택 안 함
        dfs(idx + 1, curCal, curVal, count);
    }
}
