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
            int m = Integer.parseInt(st.nextToken());

            int[] a = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());

            int[] b = new int[m];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) b[i] = Integer.parseInt(st.nextToken());

            StringBuilder out = new StringBuilder();
            int pa = 0, pb = 0;
            boolean first = true;

            while (pa < n && pb < m) {
                if (!first) out.append(" ");
                if (a[pa] <= b[pb]) {
                    out.append(a[pa++]);
                } else {
                    out.append(b[pb++]);
                }
                first = false;
            }
            while (pa < n) {
                if (!first) out.append(" ");
                out.append(a[pa++]);
                first = false;
            }
            while (pb < m) {
                if (!first) out.append(" ");
                out.append(b[pb++]);
                first = false;
            }

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
