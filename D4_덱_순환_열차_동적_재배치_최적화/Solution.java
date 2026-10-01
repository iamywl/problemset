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
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            boolean isReversed = false;

            for (int i = 0; i < m; i++) {
                String cmdLine = br.readLine().trim();
                StringTokenizer st = new StringTokenizer(cmdLine);
                String cmd = st.nextToken();

                if (cmd.equals("INVERT")) {
                    isReversed = !isReversed;
                } else if (cmd.equals("DOCK_FRONT")) {
                    int w = Integer.parseInt(st.nextToken());
                    if (!isReversed) dq.addFirst(w);
                    else dq.addLast(w);
                } else if (cmd.equals("DOCK_BACK")) {
                    int w = Integer.parseInt(st.nextToken());
                    if (!isReversed) dq.addLast(w);
                    else dq.addFirst(w);
                } else if (cmd.equals("UNCOUPLE_FRONT")) {
                    if (!dq.isEmpty()) {
                        if (!isReversed) dq.pollFirst();
                        else dq.pollLast();
                    }
                } else if (cmd.equals("UNCOUPLE_BACK")) {
                    if (!dq.isEmpty()) {
                        if (!isReversed) dq.pollLast();
                        else dq.pollFirst();
                    }
                }
            }

            int sz = dq.size();
            int[] arr = new int[sz];
            int idx = 0;
            while (!dq.isEmpty()) {
                arr[idx++] = !isReversed ? dq.pollFirst() : dq.pollLast();
            }

            long frontSum = 0;
            int countF = Math.min(3, sz);
            for (int i = 0; i < countF; i++) frontSum += arr[i];

            long backSum = 0;
            int countB = Math.min(3, sz);
            for (int i = 0; i < countB; i++) backSum += arr[sz - 1 - i];

            sb.append("#").append(tc).append(" ").append(frontSum).append(" ").append(backSum).append("\n");
        }
        System.out.print(sb);
    }
}
