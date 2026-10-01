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
            int[] a = new int[n];
            int[] freq = new int[1000001];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                freq[a[i]]++;
            }

            int[] ngf = new int[n];
            Arrays.fill(ngf, -1);

            int[] stack = new int[n];
            int top = 0;

            for (int i = 0; i < n; i++) {
                while (top > 0 && freq[a[stack[top - 1]]] < freq[a[i]]) {
                    ngf[stack[--top]] = a[i];
                }
                stack[top++] = i;
            }

            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < n; i++) {
                sb.append(ngf[i]).append(i == n - 1 ? "" : " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
