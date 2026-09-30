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
            // 전체 원순열: (n-1)!
            // 1과 2가 이웃하는 경우: 1과 2를 한 덩어리로 묶으면 (n-1)개 원순열 (n-2)! * 2
            // 답 = (n-1)! - 2 * (n-2)! = (n-3) * (n-2)!
            long ans = n - 3;
            for (int i = 1; i <= n - 2; i++) {
                ans *= i;
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
