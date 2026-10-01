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
            int k = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] stack = new int[k];
            int top = 0;

            for (int i = 0; i < k; i++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 0) {
                    top--;
                } else {
                    stack[top++] = val;
                }
            }

            long sum = 0;
            for (int i = 0; i < top; i++) {
                sum += stack[i];
            }

            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }
        System.out.print(sb);
    }
}
