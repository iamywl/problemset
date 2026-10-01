import java.io.*;
import java.util.*;

public class Solution {
    static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            String s = br.readLine().trim();

            int vCount = 0;
            for (int i = 0; i < k; i++) {
                if (isVowel(s.charAt(i))) vCount++;
            }

            int maxV = vCount;
            for (int i = k; i < n; i++) {
                if (isVowel(s.charAt(i))) vCount++;
                if (isVowel(s.charAt(i - k))) vCount--;
                if (vCount > maxV) maxV = vCount;
            }

            sb.append("#").append(tc).append(" ").append(maxV).append("\n");
        }
        System.out.print(sb);
    }
}
