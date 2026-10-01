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

            int slow = 0;
            for (int fast = 1; fast < n; fast++) {
                if (arr[fast] != arr[slow]) {
                    slow++;
                    arr[slow] = arr[fast];
                }
            }

            int k = slow + 1;
            StringBuilder out = new StringBuilder();
            out.append(k);
            for (int i = 0; i < k; i++) {
                out.append(" ").append(arr[i]);
            }

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
