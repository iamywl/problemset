import java.io.*;
import java.util.*;

public class Solution {
    // 유클리드 호제법을 이용한 최대공약수 계산
    public static long gcd(long a, long b) {
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    // 최소공배수 계산 (오버플로우 방지를 위해 나눗셈 먼저 수행)
    public static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

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
            int n = Integer.parseInt(line.trim());

            StringTokenizer st = new StringTokenizer(br.readLine());
            long g = Long.parseLong(st.nextToken());
            long l = g;

            for (int i = 1; i < n; i++) {
                long val = Long.parseLong(st.nextToken());
                g = gcd(g, val);
                l = lcm(l, val);
            }

            sb.append("#").append(tc).append(" ").append(g).append(" ").append(l).append("\n");
        }
        System.out.print(sb);
    }
}
