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
            int k = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int l = 0;
            int r = n - 1;
            int mid = (l + r) / 2;

            sb.append("#").append(tc).append(" ");
            if (k == arr[mid]) {
                sb.append("EQUAL ").append(mid);
            } else if (k < arr[mid]) {
                sb.append("LEFT ").append(l).append(" ").append(mid - 1);
            } else {
                sb.append("RIGHT ").append(mid + 1).append(" ").append(r);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
