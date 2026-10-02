import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Solution {
    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
    }

    static ArrayList<Point> houses;
    static ArrayList<Point> spots;
    static int M, minTotal;
    static int[] selected;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            houses = new ArrayList<>();
            spots = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    int val = Integer.parseInt(st.nextToken());
                    if (val == 1) houses.add(new Point(r, c));
                    else if (val == 2) spots.add(new Point(r, c));
                }
            }

            minTotal = Integer.MAX_VALUE;
            selected = new int[M];
            comb(0, 0);

            sb.append("#").append(tc).append(" ").append(minTotal).append("\n");
        }
        System.out.print(sb);
    }

    static void comb(int idx, int count) {
        if (count == M) {
            int total = 0;
            for (Point h : houses) {
                int dist = Integer.MAX_VALUE;
                for (int i = 0; i < M; i++) {
                    Point s = spots.get(selected[i]);
                    dist = Math.min(dist, Math.abs(h.r - s.r) + Math.abs(h.c - s.c));
                }
                total += dist;
            }
            minTotal = Math.min(minTotal, total);
            return;
        }

        for (int i = idx; i < spots.size(); i++) {
            selected[count] = i;
            comb(i + 1, count + 1);
        }
    }
}
