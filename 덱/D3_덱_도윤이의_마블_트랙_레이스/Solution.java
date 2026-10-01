import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int limit = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            ArrayDeque<Integer> dq = new ArrayDeque<>();
            long curWeight = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String dir = st.nextToken();
                int w = Integer.parseInt(st.nextToken());

                if (dir.equals("L")) {
                    dq.addFirst(w);
                } else {
                    dq.addLast(w);
                }
                curWeight += w;

                while (curWeight > limit && !dq.isEmpty()) {
                    if (dq.size() == 1) {
                        curWeight -= dq.pollFirst();
                        break;
                    }
                    int leftW = dq.peekFirst();
                    int rightW = dq.peekLast();
                    if (leftW > rightW) {
                        curWeight -= dq.pollFirst();
                    } else if (rightW > leftW) {
                        curWeight -= dq.pollLast();
                    } else {
                        // 같으면 투입 방향 반대편 배출
                        if (dir.equals("L")) {
                            curWeight -= dq.pollLast();
                        } else {
                            curWeight -= dq.pollFirst();
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dq.size()).append(" ").append(curWeight).append("\n");
        }
        System.out.print(sb);
    }
}
