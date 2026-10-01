import java.io.*;
import java.util.*;

public class Solution {
    static class Lesson implements Comparable<Lesson> {
        int s, t;
        Lesson(int s, int t) { this.s = s; this.t = t; }
        @Override
        public int compareTo(Lesson o) {
            if (this.s != o.s) return Integer.compare(this.s, o.s);
            return Integer.compare(this.t, o.t);
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
            Lesson[] lessons = new Lesson[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                lessons[i] = new Lesson(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            Arrays.sort(lessons);

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            pq.offer(lessons[0].t);

            for (int i = 1; i < n; i++) {
                if (pq.peek() <= lessons[i].s) {
                    pq.poll();
                }
                pq.offer(lessons[i].t);
            }

            sb.append("#").append(tc).append(" ").append(pq.size()).append("\n");
        }
        System.out.print(sb);
    }
}
