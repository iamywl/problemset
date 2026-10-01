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
            for (int i = 1; i <= n; i++) q.offer(i);

            while (q.size() > 1) {
                q.poll();
                q.offer(q.poll());
            }

            sb.append("#").append(tc).append(" ").append(q.peek()).append("\n");
        }
        System.out.print(sb);
    }
}
