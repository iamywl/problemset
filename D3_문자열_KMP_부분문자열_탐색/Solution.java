import java.io.*;
import java.util.*;

public class Solution {
    // KMP 실패 함수(pi 배열) 계산
    public static int[] computePi(String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        int j = 0;
        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                pi[i] = j;
            }
        }
        return pi;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            String text = br.readLine();
            while (text != null && text.trim().isEmpty()) {
                text = br.readLine();
            }
            if (text == null) break;
            text = text.trim();

            String pattern = br.readLine();
            while (pattern != null && pattern.trim().isEmpty()) {
                pattern = br.readLine();
            }
            if (pattern == null) break;
            pattern = pattern.trim();

            int[] pi = computePi(pattern);
            int n = text.length();
            int m = pattern.length();

            int cnt = 0;
            int firstPos = -1;
            int j = 0;

            for (int i = 0; i < n; i++) {
                while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                    j = pi[j - 1];
                }
                if (text.charAt(i) == pattern.charAt(j)) {
                    if (j == m - 1) {
                        cnt++;
                        if (firstPos == -1) {
                            firstPos = (i - m + 1) + 1; // 1-based index
                        }
                        j = pi[j];
                    } else {
                        j++;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(cnt).append(" ").append(firstPos).append("\n");
        }
        System.out.print(sb);
    }
}
