import java.io.*;
import java.util.*;

public class Solution {
    static class Process {
        int id, arrival, remain;
        Process(int id, int arrival, int remain) {
            this.id = id;
            this.arrival = arrival;
            this.remain = remain;
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
            int qVal = Integer.parseInt(st.nextToken());

            Process[] procs = new Process[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                procs[i] = new Process(i, a, b);
            }

            Deque<Process> readyQ = new ArrayDeque<>();
            int procIdx = 0;
            int currentTime = 0;
            long totalTurnaround = 0;

            while (procIdx < n || !readyQ.isEmpty()) {
                if (readyQ.isEmpty() && currentTime < procs[procIdx].arrival) {
                    currentTime = procs[procIdx].arrival;
                }

                while (procIdx < n && procs[procIdx].arrival <= currentTime) {
                    readyQ.offer(procs[procIdx++]);
                }

                Process cur = readyQ.poll();
                int execTime = Math.min(cur.remain, qVal);
                currentTime += execTime;
                cur.remain -= execTime;

                // 새 도착 프로세스 먼저 큐에 추가
                while (procIdx < n && procs[procIdx].arrival <= currentTime) {
                    readyQ.offer(procs[procIdx++]);
                }

                if (cur.remain > 0) {
                    readyQ.offer(cur);
                } else {
                    totalTurnaround += (currentTime - cur.arrival);
                }
            }

            sb.append("#").append(tc).append(" ").append(totalTurnaround).append("\n");
        }
        System.out.print(sb);
    }
}
