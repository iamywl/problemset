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
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            String s = br.readLine().trim();

            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            List<Character> list = new ArrayList<>();
            for (char c : chars) list.add(c);

            long[] fact = new long[n + 1];
            fact[0] = 1;
            for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i;

            StringBuilder res = new StringBuilder();
            k--; // 0-based
            for (int i = n; i >= 1; i--) {
                long f = fact[i - 1];
                int idx = (int)(k / f);
                res.append(list.get(idx));
                list.remove(idx);
                k %= f;
            }

            sb.append("#").append(tc).append(" ").append(res.toString()).append("\n");
        }
        System.out.print(sb);
    }
}
