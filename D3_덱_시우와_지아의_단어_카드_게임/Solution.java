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
            StringTokenizer st = new StringTokenizer(br.readLine());

            ArrayDeque<Character> dq = new ArrayDeque<>();
            char first = st.nextToken().charAt(0);
            dq.add(first);

            for (int i = 1; i < n; i++) {
                char ch = st.nextToken().charAt(0);
                if (ch <= dq.peekFirst()) {
                    dq.addFirst(ch);
                } else {
                    dq.addLast(ch);
                }
            }

            StringBuilder word = new StringBuilder();
            for (char ch : dq) {
                word.append(ch);
            }

            sb.append("#").append(tc).append(" ").append(word).append("\n");
        }
        System.out.print(sb);
    }
}
