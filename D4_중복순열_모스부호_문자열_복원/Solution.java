import java.io.*;
import java.util.*;

public class Solution {
    static String[] codes = {".-", "-...", "-.-.", "-.."};
    static String target;
    static int L;
    static int wordCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            L = Integer.parseInt(br.readLine().trim());
            target = br.readLine().trim();

            wordCount = 0;
            dfs(0);

            sb.append("#").append(tc).append(" ").append(wordCount).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int idx) {
        if (idx == L) {
            wordCount++;
            return;
        }

        for (String c : codes) {
            int len = c.length();
            if (idx + len <= L && target.startsWith(c, idx)) {
                dfs(idx + len);
            }
        }
    }
}
