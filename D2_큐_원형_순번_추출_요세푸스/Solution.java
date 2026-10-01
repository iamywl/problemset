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
            int k = Integer.parseInt(st.nextToken());

            Deque<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) q.offer(i);

            sb.append("#").append(tc);
            while (!q.isEmpty()) {
                for (int i = 0; i < k - 1; i++) {
                    q.offer(q.poll());
                }
                sb.append(" ").append(q.poll());
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
