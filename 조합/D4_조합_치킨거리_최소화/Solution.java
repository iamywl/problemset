import java.io.*;
import java.util.*;

public class Solution {
    static int N, M;
    static List<int[]> houses, chickens;
    static int[] pick;
    static int minCityDist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            houses = new ArrayList<>();
            chickens = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    int val = Integer.parseInt(st.nextToken());
                    if (val == 1) houses.add(new int[]{r, c});
                    else if (val == 2) chickens.add(new int[]{r, c});
                }
            }

            pick = new int[M];
            minCityDist = Integer.MAX_VALUE;

            comb(0, 0);

            sb.append("#").append(tc).append(" ").append(minCityDist).append("\n");
        }
        System.out.print(sb);
    }

    static void comb(int start, int depth) {
        if (depth == M) {
            int cityDist = 0;
            for (int[] h : houses) {
                int hDist = Integer.MAX_VALUE;
                for (int idx : pick) {
                    int[] ch = chickens.get(idx);
                    int d = Math.abs(h[0] - ch[0]) + Math.abs(h[1] - ch[1]);
                    if (d < hDist) hDist = d;
                }
                cityDist += hDist;
            }
            if (cityDist < minCityDist) minCityDist = cityDist;
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            pick[depth] = i;
            comb(i + 1, depth + 1);
        }
    }
}
