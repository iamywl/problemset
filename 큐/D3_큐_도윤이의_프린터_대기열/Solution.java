import java.io.*;
import java.util.*;

public class Solution {
    static class Doc {
        int id, priority;
        Doc(int id, int priority) {
            this.id = id;
            this.priority = priority;
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
            int m = Integer.parseInt(st.nextToken());

            Deque<Doc> q = new ArrayDeque<>();
            int[] priorityCount = new int[10];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int p = Integer.parseInt(st.nextToken());
                q.offer(new Doc(i, p));
                priorityCount[p]++;
            }

            int printOrder = 0;
            while (!q.isEmpty()) {
                Doc cur = q.poll();
                boolean hasHigher = false;
                for (int p = cur.priority + 1; p <= 9; p++) {
                    if (priorityCount[p] > 0) {
                        hasHigher = true;
                        break;
                    }
                }

                if (hasHigher) {
                    q.offer(cur);
                } else {
                    printOrder++;
                    priorityCount[cur.priority]--;
                    if (cur.id == m) {
                        sb.append("#").append(tc).append(" ").append(printOrder).append("\n");
                        break;
                    }
                }
            }
        }
        System.out.print(sb);
    }
}
