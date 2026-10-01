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
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            ArrayDeque<Integer> dq = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) dq.addLast(i);

            boolean isReversed = false;

            for (int i = 0; i < m; i++) {
                String cmd = br.readLine().trim();
                if (cmd.equals("REVERSE")) {
                    isReversed = !isReversed;
                } else if (cmd.equals("TOP_TO_BOTTOM")) {
                    if (!isReversed) {
                        dq.addLast(dq.pollFirst());
                    } else {
                        dq.addFirst(dq.pollLast());
                    }
                } else if (cmd.equals("BOTTOM_TO_TOP")) {
                    if (!isReversed) {
                        dq.addFirst(dq.pollLast());
                    } else {
                        dq.addLast(dq.pollFirst());
                    }
                }
            }

            int top = !isReversed ? dq.peekFirst() : dq.peekLast();
            int bottom = !isReversed ? dq.peekLast() : dq.peekFirst();

            sb.append("#").append(tc).append(" ").append(top).append(" ").append(bottom).append("\n");
        }
        System.out.print(sb);
    }
}
