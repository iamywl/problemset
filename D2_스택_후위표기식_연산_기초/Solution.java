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
            String nStr = br.readLine();
            if (nStr == null) break;
            int n = Integer.parseInt(nStr.trim());
            String expr = br.readLine().trim();

            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                char ch = expr.charAt(i);
                if (ch >= '0' && ch <= '9') {
                    stack.push(ch - '0');
                } else {
                    int b = stack.pop();
                    int a = stack.pop();
                    if (ch == '+') stack.push(a + b);
                    else if (ch == '-') stack.push(a - b);
                    else if (ch == '*') stack.push(a * b);
                }
            }

            sb.append("#").append(tc).append(" ").append(stack.pop()).append("\n");
        }
        System.out.print(sb);
    }
}
