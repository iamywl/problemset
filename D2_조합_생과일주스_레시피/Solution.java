import java.io.*;
import java.util.*;

public class Solution {
    static int N, R, K;
    static int[] sweet;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            sweet = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                sweet[i] = Integer.parseInt(st.nextToken());
            }

            ans = 0;
            comb(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    static void comb(int start, int count, int sum) {
        if (count == R) {
            if (sum >= K) ans++;
            return;
        }
        for (int i = start; i < N; i++) {
            comb(i + 1, count + 1, sum + sweet[i]);
        }
    }
}
