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
            int x = Integer.parseInt(st.nextToken());

            int[] parent = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                parent[i] = Integer.parseInt(st.nextToken());
            }

            int curr = x;
            int hops = 0;
            while (parent[curr] != curr) {
                curr = parent[curr];
                hops++;
            }

            sb.append("#").append(tc).append(" ").append(curr).append(" ").append(hops).append("\n");
        }
        System.out.print(sb);
    }
}
