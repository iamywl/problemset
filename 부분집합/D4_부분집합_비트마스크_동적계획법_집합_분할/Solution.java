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
            int c = Integer.parseInt(st.nextToken());

            int[] t = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                t[i] = Integer.parseInt(st.nextToken());
            }

            int totalMask = 1 << n;
            // dp[mask] = {servers, current_bin_weight}
            int[] servers = new int[totalMask];
            int[] lastWeight = new int[totalMask];
            Arrays.fill(servers, n + 1);

            servers[0] = 1;
            lastWeight[0] = 0;

            for (int mask = 0; mask < totalMask; mask++) {
                if (servers[mask] > n) continue;

                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) == 0) {
                        int nxt = mask | (1 << i);
                        int s = servers[mask];
                        int w = lastWeight[mask] + t[i];

                        if (w > c) {
                            s++;
                            w = t[i];
                        }

                        if (s < servers[nxt] || (s == servers[nxt] && w < lastWeight[nxt])) {
                            servers[nxt] = s;
                            lastWeight[nxt] = w;
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(servers[totalMask - 1]).append("\n");
        }
        System.out.print(sb);
    }
}
