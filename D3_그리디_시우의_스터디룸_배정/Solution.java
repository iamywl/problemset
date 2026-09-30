import java.io.*;
import java.util.*;

public class Solution {
    static class Interval implements Comparable<Interval> {
        long start, end;
        Interval(long start, long end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Interval o) {
            if (this.start != o.start) return Long.compare(this.start, o.start);
            return Long.compare(this.end, o.end);
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
            Interval[] list = new Interval[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                long s = Long.parseLong(st.nextToken());
                long e = Long.parseLong(st.nextToken());
                list[i] = new Interval(s, e);
            }

            Arrays.sort(list);

            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                if (!pq.isEmpty() && pq.peek() <= list[i].start) {
                    pq.poll();
                }
                pq.offer(list[i].end);
            }

            sb.append("#").append(tc).append(" ").append(pq.size()).append("\n");
        }
        System.out.print(sb);
    }
}
