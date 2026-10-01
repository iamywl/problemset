import java.io.*;
import java.util.*;

public class Solution {
    static boolean matches(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int TC = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= TC; tc++) {
            String s = br.readLine().trim();
            String t = br.readLine().trim();

            int n = s.length();
            int k = t.length();

            int count = 0;
            if (n >= k) {
                int[] tFreq = new int[26];
                int[] wFreq = new int[26];

                for (int i = 0; i < k; i++) {
                    tFreq[t.charAt(i) - 'a']++;
                    wFreq[s.charAt(i) - 'a']++;
                }

                if (matches(tFreq, wFreq)) count++;

                for (int i = k; i < n; i++) {
                    wFreq[s.charAt(i) - 'a']++;
                    wFreq[s.charAt(i - k) - 'a']--;
                    if (matches(tFreq, wFreq)) count++;
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
}
