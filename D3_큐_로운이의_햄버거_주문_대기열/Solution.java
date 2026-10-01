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
            long currentTime = 0;
            long totalWait = 0;

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                long arrTime = Long.parseLong(st.nextToken());
                long cookTime = Long.parseLong(st.nextToken());

                if (currentTime < arrTime) {
                    currentTime = arrTime;
                }
                currentTime += cookTime;
                totalWait += (currentTime - arrTime);
            }

            sb.append("#").append(tc).append(" ").append(totalWait).append("\n");
        }
        System.out.print(sb);
    }
}
