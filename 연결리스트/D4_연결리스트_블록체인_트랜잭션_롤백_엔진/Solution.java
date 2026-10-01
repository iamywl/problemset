import java.io.*;
import java.util.*;

public class Solution {
    static class Block {
        int id;
        long amount;
        Block prev;
        Block(int id, long amount, Block prev) {
            this.id = id;
            this.amount = amount;
            this.prev = prev;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int m = Integer.parseInt(br.readLine().trim());
            Block tip = null;
            int count = 0;
            long totalAmount = 0;

            StringBuilder out = new StringBuilder();
            boolean first = true;

            for (int i = 0; i < m; i++) {
                String cmdLine = br.readLine().trim();
                StringTokenizer st = new StringTokenizer(cmdLine);
                String cmd = st.nextToken();

                if (cmd.equals("APPEND")) {
                    int id = Integer.parseInt(st.nextToken());
                    long amt = Long.parseLong(st.nextToken());
                    tip = new Block(id, amt, tip);
                    count++;
                    totalAmount += amt;
                } else if (cmd.equals("ROLLBACK")) {
                    int targetId = Integer.parseInt(st.nextToken());
                    // 타깃 존재 여부 확인
                    boolean exists = false;
                    Block cur = tip;
                    while (cur != null) {
                        if (cur.id == targetId) { exists = true; break; }
                        cur = cur.prev;
                    }
                    if (exists) {
                        while (tip != null) {
                            int curId = tip.id;
                            totalAmount -= tip.amount;
                            count--;
                            tip = tip.prev;
                            if (curId == targetId) break;
                        }
                    }
                } else if (cmd.equals("AUDIT")) {
                    if (!first) out.append(" ");
                    out.append(count).append(" ").append(totalAmount);
                    first = false;
                }
            }

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
