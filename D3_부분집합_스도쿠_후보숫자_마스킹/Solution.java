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
            int usedMask = 0;
            for (int lineIdx = 0; lineIdx < 3; lineIdx++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int cnt = Integer.parseInt(st.nextToken());
                for (int i = 0; i < cnt; i++) {
                    int num = Integer.parseInt(st.nextToken());
                    usedMask |= (1 << num);
                }
            }

            int candidateCount = 0;
            for (int num = 1; num <= 9; num++) {
                if ((usedMask & (1 << num)) == 0) {
                    candidateCount++;
                }
            }

            sb.append("#").append(tc).append(" ").append(candidateCount).append("\n");
        }
        System.out.print(sb);
    }
}
