import java.io.*;
import java.util.*;

public class Solution {
    static int callCount;

    static long solve(int[] arr, int left, int right) {
        callCount++;
        if (left == right) {
            return arr[left];
        }
        int mid = (left + right) / 2;
        long sumL = solve(arr, left, mid);
        long sumR = solve(arr, mid + 1, right);
        return sumL + sumR;
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
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            callCount = 0;
            long totalSum = solve(arr, 0, n - 1);

            sb.append("#").append(tc).append(" ").append(totalSum).append(" ").append(callCount).append("\n");
        }
        System.out.print(sb);
    }
}
