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

            // 1. Infix to Postfix
            StringBuilder postfix = new StringBuilder();
            Deque<Character> opStack = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                char ch = expr.charAt(i);
                if (Character.isDigit(ch)) {
                    postfix.append(ch);
                } else if (ch == '(') {
                    opStack.push(ch);
                } else if (ch == ')') {
                    while (!opStack.isEmpty() && opStack.peek() != '(') {
                        postfix.append(opStack.pop());
                    }
                    if (!opStack.isEmpty()) opStack.pop(); // pop '('
                } else { // '+' or '*'
                    while (!opStack.isEmpty() && prec(opStack.peek()) >= prec(ch)) {
                        postfix.append(opStack.pop());
                    }
                    opStack.push(ch);
                }
            }
            while (!opStack.isEmpty()) {
                postfix.append(opStack.pop());
            }

            // 2. Eval Postfix
            Deque<Long> valStack = new ArrayDeque<>();
            for (int i = 0; i < postfix.length(); i++) {
                char ch = postfix.charAt(i);
                if (Character.isDigit(ch)) {
                    valStack.push((long)(ch - '0'));
                } else {
                    long b = valStack.pop();
                    long a = valStack.pop();
                    if (ch == '+') valStack.push(a + b);
                    else if (ch == '*') valStack.push(a * b);
                }
            }

            sb.append("#").append(tc).append(" ").append(valStack.pop()).append("\n");
        }
        System.out.print(sb);
    }

    private static int prec(char op) {
        if (op == '*') return 2;
        if (op == '+') return 1;
        return 0; // '('
    }
}
