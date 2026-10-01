import java.io.*;
import java.util.*;

public class Solution {
    static void printRev(int[] arr, int idx, StringBuilder sb) {
        if (idx < 0) return;
        sb.append(arr[idx]).append(" ");
        printRev(arr, idx - 1, sb);
    }

    static long sumRec(int[] arr, int idx) {
        if (idx < 0) return 0;
        return arr[idx] + sumRec(arr, idx - 1);
    }

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

            StringBuilder out = new StringBuilder();
            printRev(arr, n - 1, out);
            long total = sumRec(arr, n - 1);

            sb.append("#").append(tc).append(" ").append(out.toString()).append(total).append("\n");
        }
        System.out.print(sb);
    }
}
