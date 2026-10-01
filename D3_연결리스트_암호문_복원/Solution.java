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
            List<Integer> list = new LinkedList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int m = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                String cmd = st.nextToken(); // 'I'
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                for (int j = 0; j < y; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    list.add(x + j, val);
                }
            }

            sb.append("#").append(tc);
            int count = Math.min(10, list.size());
            for (int i = 0; i < count; i++) {
                sb.append(" ").append(list.get(i));
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
