import java.io.*;
import java.util.*;

public class Solution {
    // 2차원 외적을 이용한 CCW 계산 (-1, 0, 1)
    public static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long cross = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        if (cross > 0) return 1;
        if (cross < 0) return -1;
        return 0;
    }

    // 두 선분 AB와 CD의 교차 여부 판별
    public static boolean isIntersect(long x1, long y1, long x2, long y2,
                                     long x3, long y3, long x4, long y4) {
        int d1 = ccw(x1, y1, x2, y2, x3, y3);
        int d2 = ccw(x1, y1, x2, y2, x4, y4);
        int d3 = ccw(x3, y3, x4, y4, x1, y1);
        int d4 = ccw(x3, y3, x4, y4, x2, y2);

        // 네 점이 모두 일직선상에 있는 경우
        if (d1 * d2 == 0 && d3 * d4 == 0) {
            return Math.min(x1, x2) <= Math.max(x3, x4) &&
                   Math.min(x3, x4) <= Math.max(x1, x2) &&
                   Math.min(y1, y2) <= Math.max(y3, y4) &&
                   Math.min(y3, y4) <= Math.max(y1, y2);
        }

        // 일반 교차 또는 한 점이 선분 위에 접하는 경우
        return (d1 * d2 <= 0) && (d3 * d4 <= 0);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            line = br.readLine();
            while (line != null && line.trim().isEmpty()) {
                line = br.readLine();
            }
            if (line == null) break;
            StringTokenizer st1 = new StringTokenizer(line);
            long x1 = Long.parseLong(st1.nextToken());
            long y1 = Long.parseLong(st1.nextToken());
            long x2 = Long.parseLong(st1.nextToken());
            long y2 = Long.parseLong(st1.nextToken());

            StringTokenizer st2 = new StringTokenizer(br.readLine());
            long x3 = Long.parseLong(st2.nextToken());
            long y3 = Long.parseLong(st2.nextToken());
            long x4 = Long.parseLong(st2.nextToken());
            long y4 = Long.parseLong(st2.nextToken());

            boolean ans = isIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
            sb.append("#").append(tc).append(" ").append(ans ? 1 : 0).append("\n");
        }
        System.out.print(sb);
    }
}
