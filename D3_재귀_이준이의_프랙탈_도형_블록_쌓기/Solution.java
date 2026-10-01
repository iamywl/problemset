import java.io.*;
import java.util.*;

public class Solution {
    static int check(int r, int c) {
        while (r > 0 || c > 0) {
            if (r % 3 == 1 && c % 3 == 1) return 0;
            r /= 3;
            c /= 3;
        }
        return 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            sb.append("#").append(tc).append(" ").append(check(r, c)).append("\n");
        }
        System.out.print(sb);
    }
}
