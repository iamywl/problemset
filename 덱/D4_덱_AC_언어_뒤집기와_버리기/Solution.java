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
            String p = br.readLine().trim();
            int n = Integer.parseInt(br.readLine().trim());
            String arrStr = br.readLine().trim();

            ArrayDeque<Integer> dq = new ArrayDeque<>();
            if (n > 0) {
                String sub = arrStr.substring(1, arrStr.length() - 1);
                StringTokenizer st = new StringTokenizer(sub, ",");
                while (st.hasMoreTokens()) {
                    dq.addLast(Integer.parseInt(st.nextToken()));
                }
            }

            boolean isReversed = false;
            boolean isError = false;

            for (int i = 0; i < p.length(); i++) {
                char cmd = p.charAt(i);
                if (cmd == 'R') {
                    isReversed = !isReversed;
                } else if (cmd == 'D') {
                    if (dq.isEmpty()) {
                        isError = true;
                        break;
                    }
                    if (!isReversed) {
                        dq.pollFirst();
                    } else {
                        dq.pollLast();
                    }
                }
            }

            sb.append("#").append(tc).append(" ");
            if (isError) {
                sb.append("error\n");
            } else {
                sb.append("[");
                boolean first = true;
                while (!dq.isEmpty()) {
                    int val = !isReversed ? dq.pollFirst() : dq.pollLast();
                    if (!first) sb.append(",");
                    sb.append(val);
                    first = false;
                }
                sb.append("]\n");
            }
        }
        System.out.print(sb);
    }
}
