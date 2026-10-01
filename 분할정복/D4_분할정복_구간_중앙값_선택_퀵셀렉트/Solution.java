import java.io.*;
import java.util.*;

public class Solution {
    static long[] arr;
    static Random rand = new Random(42);
    
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
            
            arr = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Long.parseLong(st.nextToken());
            
            long result = quickSelect(0, n - 1, k - 1); // 0-based k-1
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }
    
    private static long quickSelect(int left, int right, int k) {
        if (left == right) return arr[left];
        
        int pivotIdx = left + rand.nextInt(right - left + 1);
        long pivot = arr[pivotIdx];
        
        // 3-way partitioning
        int lt = left, gt = right, i = left;
        while (i <= gt) {
            if (arr[i] < pivot) {
                swap(lt++, i++);
            } else if (arr[i] > pivot) {
                swap(i, gt--);
            } else {
                i++;
            }
        }
        
        if (k < lt) {
            return quickSelect(left, lt - 1, k);
        } else if (k <= gt) {
            return pivot;
        } else {
            return quickSelect(gt + 1, right, k);
        }
    }
    
    private static void swap(int i, int j) {
        long t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
