import java.io.*;
import java.util.*;

public class Solution {
    static class Task implements Comparable<Task> {
        int deadline;
        long score;
        Task(int deadline, long score) {
            this.deadline = deadline;
            this.score = score;
        }
        @Override
        public int compareTo(Task o) {
            if (this.deadline != o.deadline) return Integer.compare(this.deadline, o.deadline);
            return Long.compare(o.score, this.score);
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
            Task[] tasks = new Task[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int d = Integer.parseInt(st.nextToken());
                long p = Long.parseLong(st.nextToken());
                tasks[i] = new Task(d, p);
            }

            Arrays.sort(tasks);

            PriorityQueue<Long> minHeap = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                minHeap.offer(tasks[i].score);
                if (minHeap.size() > tasks[i].deadline) {
                    minHeap.poll(); // 가장 점수가 작은 과제 탈락
                }
            }

            long totalScore = 0;
            while (!minHeap.isEmpty()) {
                totalScore += minHeap.poll();
            }

            sb.append("#").append(tc).append(" ").append(totalScore).append("\n");
        }
        System.out.print(sb);
    }
}
