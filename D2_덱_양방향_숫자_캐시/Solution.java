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
            int k = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            ArrayDeque<Integer> dq = new ArrayDeque<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int x = Integer.parseInt(st.nextToken());

                if (cmd.equals("PUT_FRONT")) {
                    dq.addFirst(x);
                    if (dq.size() > k) {
                        dq.pollLast();
                    }
                } else if (cmd.equals("PUT_BACK")) {
                    dq.addLast(x);
                    if (dq.size() > k) {
                        dq.pollFirst();
                    }
                }
            }

            StringBuilder out = new StringBuilder();
            boolean first = true;
            for (int val : dq) {
                if (!first) out.append(" ");
                out.append(val);
                first = false;
            }

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
