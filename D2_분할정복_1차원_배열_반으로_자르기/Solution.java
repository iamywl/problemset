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
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int mid = n / 2;
            long sumL = 0;
            long sumR = 0;

            for (int i = 0; i < mid; i++) sumL += arr[i];
            for (int i = mid; i < n; i++) sumR += arr[i];

            long diff = Math.abs(sumL - sumR);
            String side = "EQUAL";
            if (sumL > sumR) side = "LEFT";
            else if (sumL < sumR) side = "RIGHT";

            sb.append("#").append(tc).append(" ").append(diff).append(" ").append(side).append("\n");
        }
        System.out.print(sb);
    }
}
