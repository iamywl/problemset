import java.io.*;
import java.util.*;

public class Solution {
    static int findMedian(int[] freq, int targetRank) {
        int count = 0;
        for (int v = 0; v <= 5000; v++) {
            count += freq[v];
            if (count >= targetRank) return v;
        }
        return 0;
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

            int[] freq = new int[5005];
            for (int i = 0; i < k; i++) freq[arr[i]]++;

            int targetRank = (k + 1) / 2;
            StringBuilder out = new StringBuilder();
            out.append(findMedian(freq, targetRank));

            for (int i = k; i < n; i++) {
                freq[arr[i - k]]--;
                freq[arr[i]]++;
                out.append(" ").append(findMedian(freq, targetRank));
            }

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
