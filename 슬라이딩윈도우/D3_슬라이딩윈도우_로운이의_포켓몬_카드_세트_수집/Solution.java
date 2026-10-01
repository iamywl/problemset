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
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            int[] freq = new int[100005];
            int distinct = 0;

            for (int i = 0; i < k; i++) {
                if (freq[arr[i]] == 0) distinct++;
                freq[arr[i]]++;
            }

            int maxDistinct = distinct;

            for (int i = k; i < n; i++) {
                freq[arr[i - k]]--;
                if (freq[arr[i - k]] == 0) distinct--;

                if (freq[arr[i]] == 0) distinct++;
                freq[arr[i]]++;

                if (distinct > maxDistinct) maxDistinct = distinct;
            }

            sb.append("#").append(tc).append(" ").append(maxDistinct).append("\n");
        }
        System.out.print(sb);
    }
}
