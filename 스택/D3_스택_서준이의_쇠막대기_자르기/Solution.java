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
            String s = br.readLine().trim();
            int open = 0;
            long pieces = 0;

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '(') {
                    open++;
                } else {
                    open--;
                    if (s.charAt(i - 1) == '(') {
                        pieces += open;
                    } else {
                        pieces += 1;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(pieces).append("\n");
        }
        System.out.print(sb);
    }
}
