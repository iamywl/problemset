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
            String initStr = br.readLine().trim();
            int m = Integer.parseInt(br.readLine().trim());

            ArrayDeque<Character> left = new ArrayDeque<>();
            ArrayDeque<Character> right = new ArrayDeque<>();

            for (int i = 0; i < initStr.length(); i++) {
                left.addLast(initStr.charAt(i));
            }

            for (int i = 0; i < m; i++) {
                String cmdLine = br.readLine().trim();
                char op = cmdLine.charAt(0);

                if (op == 'L') {
                    if (!left.isEmpty()) {
                        right.addFirst(left.pollLast());
                    }
                } else if (op == 'D') {
                    if (!right.isEmpty()) {
                        left.addLast(right.pollFirst());
                    }
                } else if (op == 'B') {
                    if (!left.isEmpty()) {
                        left.pollLast();
                    }
                } else if (op == 'P') {
                    char c = cmdLine.charAt(2);
                    left.addLast(c);
                }
            }

            StringBuilder out = new StringBuilder();
            for (char c : left) out.append(c);
            for (char c : right) out.append(c);

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
