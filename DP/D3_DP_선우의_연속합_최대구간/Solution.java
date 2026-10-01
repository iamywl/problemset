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
            StringTokenizer st = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(st.nextToken());
            int currentSum = first;
            int maxSum = first;

            for (int i = 1; i < n; i++) {
                int val = Integer.parseInt(st.nextToken());
                currentSum = Math.max(val, currentSum + val);
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }

            sb.append("#").append(tc).append(" ").append(maxSum).append("\n");
        }
        System.out.print(sb);
    }
}
