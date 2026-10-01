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
            int r = Integer.parseInt(st.nextToken());

            // 1번 학생이 선택되는 경우: 맨 앞(1개) 또는 맨 뒤(1개) (r=1이면 1가지)
            // 나머지 (r-1)자리는 남은 (n-1)명 중 순열: P(n-1, r-1)
            // 위치 가짓수: r == 1 ? 1 : 2
            long p = 1;
            for (int i = 0; i < r - 1; i++) {
                p *= (n - 1 - i);
            }
            long pos = (r == 1) ? 1 : 2;
            long ans = pos * p;

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
