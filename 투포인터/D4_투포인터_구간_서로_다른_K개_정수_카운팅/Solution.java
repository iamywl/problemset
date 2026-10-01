import java.io.*;
import java.util.*;

public class Solution {
    static long atMostK(int[] arr, int k, int n) {
        if (k <= 0) return 0;
        int[] freq = new int[50005];
        int distinct = 0;
        int left = 0;
        long count = 0;

        for (int right = 0; right < n; right++) {
            if (freq[arr[right]] == 0) distinct++;
            freq[arr[right]]++;

            while (distinct > k) {
                freq[arr[left]]--;
                if (freq[arr[left]] == 0) distinct--;
                left++;
            }

            count += (right - left + 1);
        }
        return count;
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
            int k = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            long exactK = atMostK(arr, k, n) - atMostK(arr, k - 1, n);

            sb.append("#").append(tc).append(" ").append(exactK).append("\n");
        }
        System.out.print(sb);
    }
}
