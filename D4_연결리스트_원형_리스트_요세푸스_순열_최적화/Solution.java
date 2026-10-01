import java.io.*;
import java.util.*;

public class Solution {
    static class Node {
        int id;
        Node prev, next;
        Node(int id) { this.id = id; }
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
            int k = Integer.parseInt(st.nextToken());

            Node head = new Node(1);
            Node prev = head;
            for (int i = 2; i <= n; i++) {
                Node node = new Node(i);
                prev.next = node;
                node.prev = prev;
                prev = node;
            }
            prev.next = head;
            head.prev = prev;

            StringBuilder out = new StringBuilder();
            out.append("<");
            Node cur = head;

            for (int step = 0; step < n; step++) {
                for (int i = 0; i < k - 1; i++) {
                    cur = cur.next;
                }
                if (step > 0) out.append(",");
                out.append(cur.id);

                // remove cur
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;
                cur = cur.next;
            }
            out.append(">");

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
