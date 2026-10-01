import java.io.*;
import java.util.*;

public class Solution {
    static long getMaxProduct(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        long maxVal = Long.MIN_VALUE;

        for (int shift = -(n - 1); shift < m; shift++) {
            long currentSum = 0;
            int overlapCount = 0;

            for (int i = 0; i < n; i++) {
                int j = i + shift;
                if (j >= 0 && j < m) {
                    currentSum += (long) A[i] * B[j];
                    overlapCount++;
                }
            }

            if (overlapCount >= 1) {
                if (currentSum > maxVal) {
                    maxVal = currentSum;
                }
            }
        }
        return maxVal;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] A = new int[n];
            int[] A_rev = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                A_rev[n - 1 - i] = A[i];
            }

            int[] B = new int[m];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            long ansOriginal = getMaxProduct(A, B);
            long ansReversed = getMaxProduct(A_rev, B);

            long finalAnswer = Math.max(ansOriginal, ansReversed);
            sb.append("#").append(tc).append(" ").append(finalAnswer).append("\n");
        }
        System.out.print(sb);
    }
}
