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
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[] belt = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) belt[i] = Integer.parseInt(st.nextToken());

            int[] count = new int[d + 1];
            int distinct = 0;

            for (int i = 0; i < k; i++) {
                if (count[belt[i]] == 0) distinct++;
                count[belt[i]]++;
            }

            int maxKinds = distinct + (count[c] == 0 ? 1 : 0);

            for (int i = 0; i < n - 1; i++) {
                // remove belt[i]
                count[belt[i]]--;
                if (count[belt[i]] == 0) distinct--;

                // add belt[(i + k) % n]
                int nextSushi = belt[(i + k) % n];
                if (count[nextSushi] == 0) distinct++;
                count[nextSushi]++;

                int curKinds = distinct + (count[c] == 0 ? 1 : 0);
                if (curKinds > maxKinds) maxKinds = curKinds;
            }

            sb.append("#").append(tc).append(" ").append(maxKinds).append("\n");
        }
        System.out.print(sb);
    }
}
