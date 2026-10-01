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
            int l = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            ArrayDeque<Integer> dq = new ArrayDeque<>();
            StringBuilder out = new StringBuilder();
            boolean first = true;

            for (int i = 0; i < n; i++) {
                // 윈도우 벗어난 인덱스 제거
                while (!dq.isEmpty() && dq.peekFirst() <= i - l) {
                    dq.pollFirst();
                }
                // 새 원소보다 작거나 같은 값 뒤에서 제거
                while (!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]) {
                    dq.pollLast();
                }
                dq.addLast(i);

                if (i >= l - 1) {
                    if (!first) out.append(" ");
                    out.append(arr[dq.peekFirst()]);
                    first = false;
                }
            }

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
