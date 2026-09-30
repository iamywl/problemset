import java.io.*;
import java.util.*;

public class Solution {
    static class Meeting implements Comparable<Meeting> {
        long start, end;
        Meeting(long start, long end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Meeting o) {
            if (this.end != o.end) return Long.compare(this.end, o.end);
            return Long.compare(this.start, o.start);
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
            Meeting[] meetings = new Meeting[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                long s = Long.parseLong(st.nextToken());
                long e = Long.parseLong(st.nextToken());
                meetings[i] = new Meeting(s, e);
            }

            Arrays.sort(meetings);

            int count = 0;
            long lastEnd = 0;
            for (int i = 0; i < n; i++) {
                if (meetings[i].start >= lastEnd) {
                    count++;
                    lastEnd = meetings[i].end;
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
}
