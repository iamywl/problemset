import java.io.*;
import java.util.*;

public class Solution {
    static class Tower {
        int id, h;
        Tower(int id, int h) {
            this.id = id;
            this.h = h;
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
            StringTokenizer st = new StringTokenizer(br.readLine());

            Deque<Tower> stack = new ArrayDeque<>();
            StringBuilder ans = new StringBuilder();

            for (int i = 1; i <= n; i++) {
                int h = Integer.parseInt(st.nextToken());
                while (!stack.isEmpty() && stack.peek().h < h) {
                    stack.pop();
                }

                if (i > 1) ans.append(" ");
                if (stack.isEmpty()) {
                    ans.append(0);
                } else {
                    ans.append(stack.peek().id);
                }
                stack.push(new Tower(i, h));
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
