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
            int lCount = Integer.parseInt(st.nextToken());
            int rCount = Integer.parseInt(st.nextToken());

            int[] suits = new int[n + 2];
            Arrays.fill(suits, 1);

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < lCount; i++) {
                int id = Integer.parseInt(st.nextToken());
                suits[id]--;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < rCount; i++) {
                int id = Integer.parseInt(st.nextToken());
                suits[id]++;
            }

            for (int i = 1; i <= n; i++) {
                if (suits[i] == 0) {
                    if (suits[i - 1] == 2) {
                        suits[i - 1]--;
                        suits[i]++;
                    } else if (suits[i + 1] == 2) {
                        suits[i + 1]--;
                        suits[i]++;
                    }
                }
            }

            int ans = 0;
            for (int i = 1; i <= n; i++) {
                if (suits[i] >= 1) ans++;
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
