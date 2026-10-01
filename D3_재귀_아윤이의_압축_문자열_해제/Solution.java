import java.io.*;
import java.util.*;

public class Solution {
    static int idx;
    static String str;

    static String parse() {
        StringBuilder sb = new StringBuilder();
        while (idx < str.length()) {
            char ch = str.charAt(idx);
            if (ch == ')') {
                idx++;
                return sb.toString();
            } else if (Character.isDigit(ch)) {
                int k = ch - '0';
                idx += 2; // 숫자와 '(' 건너뜀
                String sub = parse();
                for (int i = 0; i < k; i++) sb.append(sub);
            } else {
                sb.append(ch);
                idx++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder out = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            str = br.readLine().trim();
            idx = 0;
            String decoded = parse();
            out.append("#").append(tc).append(" ").append(decoded).append("\n");
        }
        System.out.print(out);
    }
}
