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
            LinkedList<Integer> list = new LinkedList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int m = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine());

            for (int cmdIdx = 0; cmdIdx < m; cmdIdx++) {
                if (!st.hasMoreTokens()) break;
                String type = st.nextToken();

                if (type.equals("I")) {
                    int idx = Integer.parseInt(st.nextToken());
                    int cnt = Integer.parseInt(st.nextToken());
                    idx = Math.min(idx, list.size());
                    for (int c = 0; c < cnt; c++) {
                        int val = Integer.parseInt(st.nextToken());
                        list.add(idx + c, val);
                    }
                } else if (type.equals("D")) {
                    int idx = Integer.parseInt(st.nextToken());
                    int cnt = Integer.parseInt(st.nextToken());
                    for (int c = 0; c < cnt; c++) {
                        if (idx < list.size()) {
                            list.remove(idx);
                        }
                    }
                } else if (type.equals("A")) {
                    int cnt = Integer.parseInt(st.nextToken());
                    for (int c = 0; c < cnt; c++) {
                        int val = Integer.parseInt(st.nextToken());
                        list.add(val);
                    }
                }
            }

            StringBuilder out = new StringBuilder();
            int outCount = Math.min(10, list.size());
            for (int i = 0; i < outCount; i++) {
                if (i > 0) out.append(" ");
                out.append(list.get(i));
            }

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
