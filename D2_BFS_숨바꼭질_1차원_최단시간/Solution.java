import java.io.*;
import java.util.*;

public class Solution {
    static final int MAX = 100000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (n >= k) {
                sb.append("#").append(tc).append(" ").append(n - k).append("\n");
                continue;
            }

            int[] dist = new int[MAX + 1];
            Arrays.fill(dist, -1);
            Deque<Integer> q = new ArrayDeque<>();

            q.offer(n);
            dist[n] = 0;

            while (!q.isEmpty()) {
                int cur = q.poll();
                if (cur == k) break;

                int[] nexts = {cur - 1, cur + 1, cur * 2};
                for (int next : nexts) {
                    if (next >= 0 && next <= MAX && dist[next] == -1) {
                        dist[next] = dist[cur] + 1;
                        q.offer(next);
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dist[k]).append("\n");
        }
        System.out.print(sb);
    }
}
