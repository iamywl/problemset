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
            PriorityQueue<Long> pq = new PriorityQueue<>(n);

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            long totalBits = 0;
            while (pq.size() > 1) {
                long first = pq.poll();
                long second = pq.poll();
                long sum = first + second;
                totalBits += sum;
                pq.offer(sum);
            }

            sb.append("#").append(tc).append(" ").append(totalBits).append("\n");
        }
        System.out.print(sb);
    }
}
