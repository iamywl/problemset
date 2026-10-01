import java.io.*;
import java.util.*;

public class Solution {
    static class Jewel implements Comparable<Jewel> {
        int m, v;
        Jewel(int m, int v) { this.m = m; this.v = v; }
        @Override
        public int compareTo(Jewel o) {
            return Integer.compare(this.m, o.m);
        }
    }

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

            Jewel[] jewels = new Jewel[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            int[] bags = new int[k];
            for (int i = 0; i < k; i++) {
                bags[i] = Integer.parseInt(br.readLine().trim());
            }

            Arrays.sort(jewels);
            Arrays.sort(bags);

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            long totalValue = 0;
            int jIdx = 0;

            for (int bagCap : bags) {
                while (jIdx < n && jewels[jIdx].m <= bagCap) {
                    pq.offer(jewels[jIdx].v);
                    jIdx++;
                }
                if (!pq.isEmpty()) {
                    totalValue += pq.poll();
                }
            }

            sb.append("#").append(tc).append(" ").append(totalValue).append("\n");
        }
        System.out.print(sb);
    }
}
