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
            Deque<Integer> countStack = new ArrayDeque<>();
            Deque<StringBuilder> stringStack = new ArrayDeque<>();
            StringBuilder current = new StringBuilder();
            int k = 0;

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (Character.isDigit(ch)) {
                    k = k * 10 + (ch - '0');
                } else if (ch == '[') {
                    countStack.push(k);
                    stringStack.push(current);
                    current = new StringBuilder();
                    k = 0;
                } else if (ch == ']') {
                    StringBuilder prev = stringStack.pop();
                    int repeat = countStack.pop();
                    for (int r = 0; r < repeat; r++) {
                        prev.append(current);
                    }
                    current = prev;
                } else {
                    current.append(ch);
                }
            }

            sb.append("#").append(tc).append(" ").append(current.toString()).append("\n");
        }
        System.out.print(sb);
    }
}
