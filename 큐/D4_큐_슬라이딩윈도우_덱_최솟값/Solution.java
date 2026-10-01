import java.io.*;
import java.util.*;

public class Solution {
    static class Node {
        int idx, val;
        Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
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
            int l = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            Deque<Node> dq = new ArrayDeque<>();
            StringBuilder ans = new StringBuilder();

            for (int i = 1; i <= n; i++) {
                int val = Integer.parseInt(st.nextToken());

                while (!dq.isEmpty() && dq.peekLast().val >= val) {
                    dq.pollLast();
                }

                while (!dq.isEmpty() && dq.peekFirst().idx <= i - l) {
                    dq.pollFirst();
                }

                dq.offerLast(new Node(i, val));

                if (i > 1) ans.append(" ");
                ans.append(dq.peekFirst().val);
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
