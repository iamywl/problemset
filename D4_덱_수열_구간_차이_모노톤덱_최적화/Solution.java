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

            ArrayDeque<Integer> maxDq = new ArrayDeque<>();
            ArrayDeque<Integer> minDq = new ArrayDeque<>();

            int left = 0;
            int maxLen = 0;

            for (int right = 0; right < n; right++) {
                while (!maxDq.isEmpty() && arr[maxDq.peekLast()] <= arr[right]) {
                    maxDq.pollLast();
                }
                maxDq.addLast(right);

                while (!minDq.isEmpty() && arr[minDq.peekLast()] >= arr[right]) {
                    minDq.pollLast();
                }
                minDq.addLast(right);

                while (arr[maxDq.peekFirst()] - arr[minDq.peekFirst()] > k) {
                    left++;
                    if (maxDq.peekFirst() < left) maxDq.pollFirst();
                    if (minDq.peekFirst() < left) minDq.pollFirst();
                }

                maxLen = Math.max(maxLen, right - left + 1);
            }

            sb.append("#").append(tc).append(" ").append(maxLen).append("\n");
        }
        System.out.print(sb);
    }
}
