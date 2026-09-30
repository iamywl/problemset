import java.io.*;
import java.util.*;

public class Solution {
    static class Applicant implements Comparable<Applicant> {
        int paper, interview;
        Applicant(int paper, int interview) {
            this.paper = paper;
            this.interview = interview;
        }
        @Override
        public int compareTo(Applicant o) {
            return Integer.compare(this.paper, o.paper);
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
            Applicant[] list = new Applicant[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int iv = Integer.parseInt(st.nextToken());
                list[i] = new Applicant(p, iv);
            }

            Arrays.sort(list);

            int count = 1; // 1등(서류 1위)은 무조건 선발
            int minInterview = list[0].interview;

            for (int i = 1; i < n; i++) {
                if (list[i].interview < minInterview) {
                    count++;
                    minInterview = list[i].interview;
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
}
