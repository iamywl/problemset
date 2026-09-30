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
            int n = Integer.parseInt(br.readLine().trim());

            long[] dist = new long[n - 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n - 1; i++) {
                dist[i] = Long.parseLong(st.nextToken());
            }

            long[] cost = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                cost[i] = Long.parseLong(st.nextToken());
            }

            long totalCost = 0;
            long minCost = cost[0];

            for (int i = 0; i < n - 1; i++) {
                if (cost[i] < minCost) {
                    minCost = cost[i];
                }
                totalCost += minCost * dist[i];
            }

            sb.append("#").append(tc).append(" ").append(totalCost).append("\n");
        }
        System.out.print(sb);
    }
}
