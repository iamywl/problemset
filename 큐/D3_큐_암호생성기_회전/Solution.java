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
            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < 8; i++) {
                q.offer(Integer.parseInt(st.nextToken()));
            }

            int dec = 1;
            while (true) {
                int val = q.poll() - dec;
                if (val <= 0) {
                    q.offer(0);
                    break;
                }
                q.offer(val);
                dec = (dec % 5) + 1;
            }

            sb.append("#").append(tc);
            while (!q.isEmpty()) {
                sb.append(" ").append(q.poll());
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
