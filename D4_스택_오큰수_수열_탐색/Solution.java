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
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            int[] nge = new int[n];
            Arrays.fill(nge, -1);

            int[] stack = new int[n];
            int top = 0;

            for (int i = 0; i < n; i++) {
                while (top > 0 && arr[stack[top - 1]] < arr[i]) {
                    nge[stack[--top]] = arr[i];
                }
                stack[top++] = i;
            }

            sb.append("#").append(tc);
            for (int i = 0; i < n; i++) {
                sb.append(" ").append(nge[i]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
