import java.io.*;
import java.util.*;

public class Solution {
    static class Doc {
        int id;
        int priority;
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

            Deque<Doc> queue = new ArrayDeque<>();
            int[] count = new int[10];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int p = Integer.parseInt(st.nextToken());
                queue.offer(new Doc(i, p));
                count[p]++;
            }

            int printOrder = 0;
            while (!queue.isEmpty()) {
                Doc cur = queue.poll();
                boolean hasHigher = false;
                for (int p = cur.priority + 1; p <= 9; p++) {
                    if (count[p] > 0) {
                        hasHigher = true;
                        break;
                    }
                }

                if (hasHigher) {
                    queue.offer(cur);
                } else {
                    printOrder++;
                    count[cur.priority]--;
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
