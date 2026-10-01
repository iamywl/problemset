import java.io.*;
import java.util.*;

public class Solution {
    static long[] arr;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            arr = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Long.parseLong(st.nextToken());
            
            long maxSub = maxSubarrayDivideConquer(0, n - 1);
            sb.append("#").append(tc).append(" ").append(maxSub).append("\n");
        }
        System.out.print(sb);
    }
    
    private static long maxSubarrayDivideConquer(int left, int right) {
        if (left == right) return arr[left];
        int mid = left + (right - left) / 2;
        
        long leftMax = maxSubarrayDivideConquer(left, mid);
        long rightMax = maxSubarrayDivideConquer(mid + 1, right);
        long crossMax = maxCrossingSubarray(left, mid, right);
        
        return Math.max(leftMax, Math.max(rightMax, crossMax));
    }
    
    private static long maxCrossingSubarray(int left, int mid, int right) {
        long leftSum = Long.MIN_VALUE;
        long sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += arr[i];
            if (sum > leftSum) leftSum = sum;
        }
        
        long rightSum = Long.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += arr[i];
            if (sum > rightSum) rightSum = sum;
        }
        return leftSum + rightSum;
    }
}
