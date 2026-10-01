import java.io.*;
import java.util.*;

public class Solution {
    static boolean isPalin(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            String s = br.readLine().trim();
            int l = 0, r = s.length() - 1;
            int ans = 0;

            while (l < r) {
                if (s.charAt(l) != s.charAt(r)) {
                    if (isPalin(s, l + 1, r) || isPalin(s, l, r - 1)) {
                        ans = 1;
                    } else {
                        ans = 2;
                    }
                    break;
                }
                l++;
                r--;
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
