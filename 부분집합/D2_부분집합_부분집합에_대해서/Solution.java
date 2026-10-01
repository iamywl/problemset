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
            sb.append("#").append(tc).append("\n");

            int total = 1 << n;
            for (int mask = 1; mask < total; mask++) {
                boolean first = true;
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) != 0) {
                        if (!first) sb.append(" ");
                        sb.append(i + 1);
                        first = false;
                    }
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
