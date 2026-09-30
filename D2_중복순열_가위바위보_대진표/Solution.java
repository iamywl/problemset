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
            int n = Integer.parseInt(br.readLine().trim());
            // 2가지 모양을 선택하는 방법: 3C2 = 3가지
            // 두 모양으로만 구성되는 경우: 2^n - 2 (한 모양으로만 몰빵된 2가지 제외)
            // 총 경우 = 3 * (2^n - 2)
            long pow2 = 1L << n;
            long ans = 3 * (pow2 - 2);

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
