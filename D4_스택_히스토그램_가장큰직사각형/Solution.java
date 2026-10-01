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
            long[] h = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) h[i] = Long.parseLong(st.nextToken());

            int[] stack = new int[n];
            int top = 0;
            long maxArea = 0;

            for (int i = 0; i < n; i++) {
                while (top > 0 && h[stack[top - 1]] >= h[i]) {
                    long height = h[stack[--top]];
                    long width = (top == 0) ? i : (i - 1 - stack[top - 1]);
                    maxArea = Math.max(maxArea, height * width);
                }
                stack[top++] = i;
            }

            while (top > 0) {
                long height = h[stack[--top]];
                long width = (top == 0) ? n : (n - 1 - stack[top - 1]);
                maxArea = Math.max(maxArea, height * width);
            }

            sb.append("#").append(tc).append(" ").append(maxArea).append("\n");
        }
        System.out.print(sb);
    }
}
