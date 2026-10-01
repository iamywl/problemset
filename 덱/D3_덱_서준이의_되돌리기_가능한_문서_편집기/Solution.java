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
            int m = Integer.parseInt(br.readLine().trim());
            ArrayDeque<Character> left = new ArrayDeque<>();
            ArrayDeque<Character> right = new ArrayDeque<>();

            for (int i = 0; i < m; i++) {
                String cmdLine = br.readLine().trim();
                StringTokenizer st = new StringTokenizer(cmdLine);
                String cmd = st.nextToken();

                if (cmd.equals("TYPE")) {
                    char c = st.nextToken().charAt(0);
                    left.addLast(c);
                } else if (cmd.equals("LEFT")) {
                    if (!left.isEmpty()) {
                        right.addFirst(left.pollLast());
                    }
                } else if (cmd.equals("RIGHT")) {
                    if (!right.isEmpty()) {
                        left.addLast(right.pollFirst());
                    }
                } else if (cmd.equals("DELETE")) {
                    if (!left.isEmpty()) {
                        left.pollLast();
                    }
                }
            }

            StringBuilder res = new StringBuilder();
            for (char c : left) res.append(c);
            for (char c : right) res.append(c);

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.print(sb);
    }
}
