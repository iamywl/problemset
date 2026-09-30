import java.io.*;
import java.util.*;

public class Solution {
    static class Item implements Comparable<Item> {
        double w, v;
        double density;
        Item(double w, double v) {
            this.w = w;
            this.v = v;
            this.density = v / w;
        }
        @Override
        public int compareTo(Item o) {
            return Double.compare(o.density, this.density); // 내림차순
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            double wCapacity = Double.parseDouble(st.nextToken());

            Item[] items = new Item[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                double weight = Double.parseDouble(st.nextToken());
                double val = Double.parseDouble(st.nextToken());
                items[i] = new Item(weight, val);
            }

            Arrays.sort(items);

            double totalVal = 0;
            double rem = wCapacity;

            for (int i = 0; i < n; i++) {
                if (rem <= 0) break;
                if (items[i].w <= rem) {
                    totalVal += items[i].v;
                    rem -= items[i].w;
                } else {
                    totalVal += items[i].density * rem;
                    rem = 0;
                }
            }

            sb.append("#").append(tc).append(String.format(Locale.US, " %.2f\n", totalVal));
        }
        System.out.print(sb);
    }
}
