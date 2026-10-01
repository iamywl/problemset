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
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                q.offer(Integer.parseInt(st.nextToken()));
            }

            while (q.size() >= 2) {
                int a = q.poll();
                int b = q.poll();
                int diff = Math.abs(a - b);
                if (diff > 0) {
                    q.offer(diff);
                }
            }

            int ans = q.isEmpty() ? 0 : q.poll();
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
