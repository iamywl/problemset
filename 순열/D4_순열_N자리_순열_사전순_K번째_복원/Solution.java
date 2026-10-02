import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        long[] fact = new long[21];
        fact[0] = 1;
        for (int i = 1; i <= 20; i++) fact[i] = fact[i - 1] * i;

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long k = Long.parseLong(st.nextToken());

            ArrayList<Integer> nums = new ArrayList<>();
            for (int i = 1; i <= n; i++) nums.add(i);

            sb.append("#").append(tc);
            k--; // 0-based
            for (int i = n - 1; i >= 0; i--) {
                int idx = (int) (k / fact[i]);
                sb.append(" ").append(nums.get(idx));
                nums.remove(idx);
                k %= fact[i];
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
