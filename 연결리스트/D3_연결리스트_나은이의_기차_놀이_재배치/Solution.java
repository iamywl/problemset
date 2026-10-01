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
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            ArrayList<Integer> list = new ArrayList<>(n);
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int L = Integer.parseInt(st.nextToken());
                int R = Integer.parseInt(st.nextToken());

                List<Integer> sub = new ArrayList<>(list.subList(L, R + 1));
                for (int d = R; d >= L; d--) {
                    list.remove(d);
                }
                list.addAll(sub);
            }

            StringBuilder out = new StringBuilder();
            out.append(list.get(0)).append(" ").append(list.get(1)).append(" ").append(list.get(2)).append(" ");
            out.append(list.get(n - 3)).append(" ").append(list.get(n - 2)).append(" ").append(list.get(n - 1));

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
