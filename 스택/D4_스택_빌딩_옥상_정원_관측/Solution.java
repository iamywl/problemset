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
            Deque<Integer> stack = new ArrayDeque<>();
            long total = 0;

            for (int i = 0; i < n; i++) {
                int h = Integer.parseInt(br.readLine().trim());
                while (!stack.isEmpty() && stack.peek() <= h) {
                    stack.pop();
                }
                total += stack.size();
                stack.push(h);
            }

            sb.append("#").append(tc).append(" ").append(total).append("\n");
        }
        System.out.print(sb);
    }
}
