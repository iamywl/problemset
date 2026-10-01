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
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            ArrayDeque<Integer> dq = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                dq.addLast(Integer.parseInt(st.nextToken()));
            }

            boolean isReversed = false;
            long totalSum = 0;

            for (int turn = 1; turn <= m; turn++) {
                int candy;
                if (turn % 2 == 1) { // 홀수 차례: 앞
                    candy = !isReversed ? dq.pollFirst() : dq.pollLast();
                } else { // 짝수 차례: 뒤
                    candy = !isReversed ? dq.pollLast() : dq.pollFirst();
                }

                totalSum += candy;
                if (candy % 2 == 0) {
                    isReversed = !isReversed;
                }
            }

            sb.append("#").append(tc).append(" ").append(totalSum).append("\n");
        }
        System.out.print(sb);
    }
}
