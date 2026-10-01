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
            Deque<Integer> q = new ArrayDeque<>();
            StringBuilder out = new StringBuilder();
            boolean first = true;

            for (int i = 0; i < n; i++) {
                String cmdLine = br.readLine().trim();
                StringTokenizer st = new StringTokenizer(cmdLine);
                String cmd = st.nextToken();

                if (cmd.equals("offer") || cmd.equals("push")) {
                    int x = Integer.parseInt(st.nextToken());
                    q.offerLast(x);
                } else if (cmd.equals("poll") || cmd.equals("pop")) {
                    int val = q.isEmpty() ? -1 : q.pollFirst();
                    if (!first) out.append(" ");
                    out.append(val);
                    first = false;
                } else if (cmd.equals("size")) {
                    if (!first) out.append(" ");
                    out.append(q.size());
                    first = false;
                } else if (cmd.equals("empty")) {
                    if (!first) out.append(" ");
                    out.append(q.isEmpty() ? 1 : 0);
                    first = false;
                } else if (cmd.equals("front")) {
                    int val = q.isEmpty() ? -1 : q.peekFirst();
                    if (!first) out.append(" ");
                    out.append(val);
                    first = false;
                } else if (cmd.equals("back")) {
                    int val = q.isEmpty() ? -1 : q.peekLast();
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
