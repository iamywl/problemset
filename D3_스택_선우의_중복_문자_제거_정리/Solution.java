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
            String s = br.readLine().trim();
            char[] stack = new char[s.length()];
            int top = 0;

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (top > 0 && stack[top - 1] == ch) {
                    top--;
                } else {
                    stack[top++] = ch;
                }
            }

            sb.append("#").append(tc).append(" ");
            if (top == 0) {
                sb.append("EMPTY\n");
            } else {
                sb.append(new String(stack, 0, top)).append("\n");
            }
        }
        System.out.print(sb);
    }
}
