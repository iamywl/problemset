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
            int m = Integer.parseInt(st.nextToken());

            int[] parent = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;

            List<Integer> answers = new ArrayList<Integer>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int type = Integer.parseInt(st.nextToken());
                if (type == 1) {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    parent[b] = a;
                } else {
                    int x = Integer.parseInt(st.nextToken());
                    answers.add(parent[x]);
                }
            }

            sb.append("#").append(tc);
            for (int val : answers) {
                sb.append(" ").append(val);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
