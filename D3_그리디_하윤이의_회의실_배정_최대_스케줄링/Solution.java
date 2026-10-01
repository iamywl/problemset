import java.io.*;
import java.util.*;

public class Solution {
    static class Meeting implements Comparable<Meeting> {
        int start, end;
        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Meeting o) {
            if (this.end != o.end) return Integer.compare(this.end, o.end);
            return Integer.compare(this.start, o.start);
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
            Meeting[] list = new Meeting[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                list[i] = new Meeting(s, e);
            }

            Arrays.sort(list);

            int count = 0;
            int lastEnd = 0;
            for (int i = 0; i < n; i++) {
                if (list[i].start >= lastEnd) {
                    count++;
                    lastEnd = list[i].end;
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
}
