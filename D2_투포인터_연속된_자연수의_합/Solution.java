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

            int start = 1, end = 1;
            long sum = 1;
            int count = 0;

            while (start <= n / 2) {
                if (sum == n) {
                    count++;
                    sum -= start;
                    start++;
                } else if (sum < n) {
                    end++;
                    sum += end;
                } else {
                    sum -= start;
                    start++;
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
}
