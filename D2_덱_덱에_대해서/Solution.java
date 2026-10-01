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
            int n = Integer.parseInt(br.readLine().trim());
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            StringBuilder out = new StringBuilder();
            boolean first = true;

            for (int i = 0; i < n; i++) {
                String cmdLine = br.readLine().trim();
                StringTokenizer st = new StringTokenizer(cmdLine);
                String cmd = st.nextToken();

                if (cmd.equals("push_front")) {
                    int x = Integer.parseInt(st.nextToken());
                    deque.addFirst(x);
                } else if (cmd.equals("push_back")) {
                    int x = Integer.parseInt(st.nextToken());
                    deque.addLast(x);
                } else if (cmd.equals("pop_front")) {
                    int val = deque.isEmpty() ? -1 : deque.pollFirst();
                    if (!first) out.append(" ");
                    out.append(val);
                    first = false;
                } else if (cmd.equals("pop_back")) {
                    int val = deque.isEmpty() ? -1 : deque.pollLast();
                    if (!first) out.append(" ");
                    out.append(val);
                    first = false;
                } else if (cmd.equals("size")) {
                    if (!first) out.append(" ");
                    out.append(deque.size());
                    first = false;
                } else if (cmd.equals("empty")) {
                    if (!first) out.append(" ");
                    out.append(deque.isEmpty() ? 1 : 0);
                    first = false;
                } else if (cmd.equals("front")) {
                    int val = deque.isEmpty() ? -1 : deque.peekFirst();
                    if (!first) out.append(" ");
                    out.append(val);
                    first = false;
                } else if (cmd.equals("back")) {
                    int val = deque.isEmpty() ? -1 : deque.peekLast();
                    if (!first) out.append(" ");
                    out.append(val);
                    first = false;
                }
            }
            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
