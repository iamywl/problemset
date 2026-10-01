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
            int n = Integer.parseInt(br.readLine().trim());
            int[] h = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) h[i] = Integer.parseInt(st.nextToken());

            int left = 0, right = n - 1;
            int leftMax = 0, rightMax = 0;
            long water = 0;

            while (left < right) {
                if (h[left] < h[right]) {
                    if (h[left] >= leftMax) {
                        leftMax = h[left];
                    } else {
                        water += leftMax - h[left];
                    }
                    left++;
                } else {
                    if (h[right] >= rightMax) {
                        rightMax = h[right];
                    } else {
                        water += rightMax - h[right];
                    }
                    right--;
                }
            }

            sb.append("#").append(tc).append(" ").append(water).append("\n");
        }
        System.out.print(sb);
    }
}
