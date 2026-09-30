import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        int MAX = 100000;

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            if (N >= K) {
                System.out.println("#" + tc + " " + (N - K));
                continue;
            }

            int[] dist = new int[MAX + 1];
            for (int i = 0; i <= MAX; i++) dist[i] = -1;

            Queue<Integer> q = new ArrayDeque<>();
            q.offer(N);
            dist[N] = 0;

            while (!q.isEmpty()) {
                int cur = q.poll();
                if (cur == K) break;

                int[] nexts = {cur - 1, cur + 1, cur * 2};
                for (int nxt : nexts) {
                    if (nxt >= 0 && nxt <= MAX && dist[nxt] == -1) {
                        dist[nxt] = dist[cur] + 1;
                        q.offer(nxt);
                    }
                }
            }
            System.out.println("#" + tc + " " + dist[K]);
        }
    }
}
