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
            while (s != null && s.trim().isEmpty()) {
                s = br.readLine();
            }
            if (s == null) break;
            s = s.trim();

            int ans = isValid(s) ? 1 : 0;
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    private static boolean isValid(String s) {
        char[] stack = new char[s.length()];
        int top = -1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack[++top] = c;
            } else {
                if (top == -1) return false;
                char open = stack[top--];
                if (c == ')' && open != '(') return false;
                if (c == '}' && open != '{') return false;
                if (c == ']' && open != '[') return false;
            }
        }
        return top == -1;
    }
}
