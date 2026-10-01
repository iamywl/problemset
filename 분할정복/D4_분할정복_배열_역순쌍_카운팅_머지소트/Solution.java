import java.io.*;
import java.util.*;

public class Solution {
    static long[] arr, temp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            arr = new long[n];
            temp = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Long.parseLong(st.nextToken());
            
            long invCount = mergeSort(0, n - 1);
            sb.append("#").append(tc).append(" ").append(invCount).append("\n");
        }
        System.out.print(sb);
    }
    
    private static long mergeSort(int left, int right) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        long count = mergeSort(left, mid) + mergeSort(mid + 1, right);
        
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                count += (mid - i + 1);
            }
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (int idx = left; idx <= right; idx++) arr[idx] = temp[idx];
        return count;
    }
}
