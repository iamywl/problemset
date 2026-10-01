import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static final int MAX_VAL = 10000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            line = br.readLine();
            while (line != null && line.trim().isEmpty()) {
                line = br.readLine();
            }
            if (line == null) break;
            int N = Integer.parseInt(line.trim());

            int[] count = new int[MAX_VAL + 1];

            // Fast reading of N integers
            StringTokenizer st = null;
            for (int i = 0; i < N; i++) {
                while (st == null || !st.hasMoreTokens()) {
                    String row = br.readLine();
                    if (row == null) break;
                    st = new StringTokenizer(row);
                }
                int val = Integer.parseInt(st.nextToken());
                count[val]++;
            }

            sb.append('#').append(tc);
            for (int val = 0; val <= MAX_VAL; val++) {
                int cnt = count[val];
                while (cnt-- > 0) {
                    sb.append(' ').append(val);
                }
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}
