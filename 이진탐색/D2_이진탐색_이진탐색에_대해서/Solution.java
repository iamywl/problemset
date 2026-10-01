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
            int m = Integer.parseInt(st.nextToken());
            
            long[] arr = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            sb.append("#").append(tc);
            for (int i = 0; i < m; i++) {
                long target = Long.parseLong(st.nextToken());
                int idx = binarySearch(arr, target);
                sb.append(" ").append(idx);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    
    private static int binarySearch(long[] arr, long target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid + 1; // 1-based index
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
