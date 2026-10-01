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
            int maxDepth = 0;
            int curDepth = 0;
            int[] depthCount = new int[s.length() + 1];

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '(') {
                    curDepth++;
                    if (curDepth > maxDepth) maxDepth = curDepth;
                    // 바로 닫히는 쌍인지 확인
                    if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                        depthCount[curDepth]++;
                    }
                } else {
                    curDepth--;
                }
            }

            int countAtMax = depthCount[maxDepth];

            sb.append("#").append(tc).append(" ").append(maxDepth).append(" ").append(countAtMax).append("\n");
        }
        System.out.print(sb);
    }
}
