import java.io.*;
import java.util.*;

public class Solution {
    static class Point implements Comparable<Point> {
        long x, y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Point o) {
            if (this.x != o.x) return Long.compare(this.x, o.x);
            return Long.compare(this.y, o.y);
        }
    }

    static Point[] points;
    static Point[] strip;

    static long distSq(Point p1, Point p2) {
        long dx = p1.x - p2.x;
        long dy = p1.y - p2.y;
        return dx * dx + dy * dy;
    }

    static long closestPair(int left, int right) {
        int count = right - left + 1;
        if (count <= 3) {
            long minD = Long.MAX_VALUE;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    minD = Math.min(minD, distSq(points[i], points[j]));
                }
            }
            return minD;
        }

        int mid = (left + right) >>> 1;
        long midX = points[mid].x;

        long d = Math.min(closestPair(left, mid), closestPair(mid + 1, right));

        int stripCount = 0;
        for (int i = left; i <= right; i++) {
            long dx = points[i].x - midX;
            if (dx * dx < d) {
                strip[stripCount++] = points[i];
            }
        }

        Arrays.sort(strip, 0, stripCount, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return Long.compare(p1.y, p2.y);
            }
        });

        for (int i = 0; i < stripCount; i++) {
            for (int j = i + 1; j < stripCount; j++) {
                long dy = strip[j].y - strip[i].y;
                if (dy * dy >= d) break;
                d = Math.min(d, distSq(strip[i], strip[j]));
            }
        }

        return d;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            points = new Point[n];
            strip = new Point[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                long x = Long.parseLong(st.nextToken());
                long y = Long.parseLong(st.nextToken());
                points[i] = new Point(x, y);
            }

            Arrays.sort(points);
            long ans = closestPair(0, n - 1);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
