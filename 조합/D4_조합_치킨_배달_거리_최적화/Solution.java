import java.io.*;
import java.util.*;

public class Solution {
    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
    }

    static int n, m;
    static List<Point> houses, chickens;
    static int[] picked;
    static int minCityDist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            houses = new ArrayList<>();
            chickens = new ArrayList<>();

            for (int r = 0; r < n; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < n; c++) {
                    int val = Integer.parseInt(st.nextToken());
                    if (val == 1) houses.add(new Point(r, c));
                    else if (val == 2) chickens.add(new Point(r, c));
                }
            }

            picked = new int[m];
            minCityDist = Integer.MAX_VALUE;
            dfs(0, 0);

            sb.append("#").append(tc).append(" ").append(minCityDist).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int depth, int start) {
        if (depth == m) {
            int sum = 0;
            for (Point h : houses) {
                int dist = Integer.MAX_VALUE;
                for (int i = 0; i < m; i++) {
                    Point ch = chickens.get(picked[i]);
                    int d = Math.abs(h.r - ch.r) + Math.abs(h.c - ch.c);
                    if (d < dist) dist = d;
                }
                sum += dist;
            }
            if (sum < minCityDist) minCityDist = sum;
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            picked[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }
}
