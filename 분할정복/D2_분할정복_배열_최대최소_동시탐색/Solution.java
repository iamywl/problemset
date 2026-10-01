import java.io.*;
import java.util.*;

public class Solution {
    static class Pair {
        long minVal, maxVal;
        Pair(long minVal, long maxVal) {
            this.minVal = minVal;
            this.maxVal = maxVal;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            long[] arr = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }
            
            Pair res = findMinMax(arr, 0, n - 1);
            sb.append("#").append(tc).append(" ").append(res.minVal).append(" ").append(res.maxVal).append("\n");
        }
        System.out.print(sb);
    }
    
    private static Pair findMinMax(long[] arr, int left, int right) {
        if (left == right) {
            return new Pair(arr[left], arr[left]);
        }
        if (left + 1 == right) {
            if (arr[left] < arr[right]) {
                return new Pair(arr[left], arr[right]);
            } else {
                return new Pair(arr[right], arr[left]);
            }
        }
        
        int mid = left + (right - left) / 2;
        Pair leftPair = findMinMax(arr, left, mid);
        Pair rightPair = findMinMax(arr, mid + 1, right);
        
        long totalMin = Math.min(leftPair.minVal, rightPair.minVal);
        long totalMax = Math.max(leftPair.maxVal, rightPair.maxVal);
        return new Pair(totalMin, totalMax);
    }
}
