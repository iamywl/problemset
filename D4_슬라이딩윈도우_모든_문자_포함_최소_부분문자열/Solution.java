import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int TC = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= TC; tc++) {
            String s = br.readLine().trim();
            String t = br.readLine().trim();

            int[] req = new int[26];
            int requiredTypes = 0;
            for (int i = 0; i < t.length(); i++) {
                int idx = t.charAt(i) - 'A';
                if (req[idx] == 0) requiredTypes++;
                req[idx]++;
            }

            int[] cur = new int[26];
            int matchedTypes = 0;
            int minLen = Integer.MAX_VALUE;
            int left = 0;

            for (int right = 0; right < s.length(); right++) {
                int rChar = s.charAt(right) - 'A';
                cur[rChar]++;
                if (req[rChar] > 0 && cur[rChar] == req[rChar]) {
                    matchedTypes++;
                }

                while (matchedTypes == requiredTypes) {
                    minLen = Math.min(minLen, right - left + 1);
                    int lChar = s.charAt(left) - 'A';
                    cur[lChar]++;
                    cur[lChar] -= 2; // cur[lChar]--
                    if (req[lChar] > 0 && cur[lChar] < req[lChar]) {
                        matchedTypes--;
                    }
                    left++;
                }
            }

            int ans = (minLen == Integer.MAX_VALUE) ? 0 : minLen;
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
