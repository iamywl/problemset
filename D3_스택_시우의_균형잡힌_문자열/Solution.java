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
            String s = br.readLine();
            Deque<Character> stack = new ArrayDeque<>();
            boolean ok = true;

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '(' || ch == '{' || ch == '[') {
                    stack.push(ch);
                } else if (ch == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        ok = false;
                        break;
                    }
                } else if (ch == '}') {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        ok = false;
                        break;
                    }
                } else if (ch == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        ok = false;
                        break;
                    }
                }
            }
            if (!stack.isEmpty()) ok = false;

            sb.append("#").append(tc).append(" ").append(ok ? 1 : 0).append("\n");
        }
        System.out.print(sb);
    }
}
