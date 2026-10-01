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
            int n = Integer.parseInt(br.readLine().trim());
            String str = br.readLine().trim();

            ArrayDeque<Character> dq = new ArrayDeque<>();
            for (int i = 0; i < str.length(); i++) {
                dq.addLast(str.charAt(i));
            }

            int isPalin = 1;
            while (dq.size() > 1) {
                char first = dq.pollFirst();
                char last = dq.pollLast();
                if (first != last) {
                    isPalin = 0;
                    break;
                }
            }

            sb.append("#").append(tc).append(" ").append(isPalin).append("\n");
        }
        System.out.print(sb);
    }
}
