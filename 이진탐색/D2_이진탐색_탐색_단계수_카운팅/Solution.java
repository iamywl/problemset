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

            int low = 0, high = n - 1;
            int steps = 0;
            int found = 0;

            while (low <= high) {
                steps++;
                int mid = (low + high) / 2;
                if (arr[mid] == k) {
                    found = 1;
                    break;
                } else if (arr[mid] < k) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            sb.append("#").append(tc).append(" ").append(found).append(" ").append(steps).append("\n");
        }
        System.out.print(sb);
    }
}
