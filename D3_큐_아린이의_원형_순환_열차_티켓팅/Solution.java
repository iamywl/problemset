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
            int baseK = Integer.parseInt(st.nextToken());

            Deque<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                q.offer(i);
            }

            int curK = baseK;
            int last = -1;

            while (!q.isEmpty()) {
                for (int i = 0; i < curK - 1; i++) {
                    q.offer(q.poll());
                }
                last = q.poll();
                if (last % 2 == 0) {
                    curK = baseK + 1;
                } else {
                    curK = baseK;
                }
            }

            sb.append("#").append(tc).append(" ").append(last).append("\n");
        }
        System.out.print(sb);
    }
}
