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
            int len = Integer.parseInt(br.readLine().trim());
            String expr = br.readLine().trim();

            Deque<Long> stack = new ArrayDeque<>();
            for (int i = 0; i < len; i++) {
                char ch = expr.charAt(i);
                if (ch >= '0' && ch <= '9') {
                    stack.push((long)(ch - '0'));
                } else if (ch == '+') {
                    long b = stack.pop();
                    long a = stack.pop();
                    stack.push(a + b);
                } else if (ch == '*') {
                    long b = stack.pop();
                    long a = stack.pop();
                    stack.push(a * b);
                }
            }

            sb.append("#").append(tc).append(" ").append(stack.pop()).append("\n");
        }
        System.out.print(sb);
    }
}
