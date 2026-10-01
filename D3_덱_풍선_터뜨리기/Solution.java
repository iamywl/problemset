import java.io.*;
import java.util.*;

public class Solution {
    static class Balloon {
        int id, step;
        Balloon(int id, int step) {
            this.id = id;
            this.step = step;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());

            Deque<Balloon> dq = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                dq.offer(new Balloon(i, Integer.parseInt(st.nextToken())));
            }

            StringBuilder ans = new StringBuilder();
            boolean first = true;

            while (!dq.isEmpty()) {
                Balloon cur = dq.pollFirst();
                if (!first) ans.append(" ");
                ans.append(cur.id);
                first = false;

                if (dq.isEmpty()) break;

                int step = cur.step;
                if (step > 0) {
                    for (int i = 0; i < step - 1; i++) {
                        dq.offerLast(dq.pollFirst());
                    }
                } else {
                    for (int i = 0; i < Math.abs(step); i++) {
                        dq.offerFirst(dq.pollLast());
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
