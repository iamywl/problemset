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
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int[] suits = new int[n + 2];
            Arrays.fill(suits, 1);
            suits[0] = suits[n + 1] = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < l; i++) {
                int lostId = Integer.parseInt(st.nextToken());
                suits[lostId]--;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < r; i++) {
                int reserveId = Integer.parseInt(st.nextToken());
                suits[reserveId]++;
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

            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (suits[i] >= 1) count++;
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
}
