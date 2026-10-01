import java.io.*;
import java.util.*;

public class Solution {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());

            Node head = null, tail = null;
            for (int i = 0; i < n; i++) {
                Node node = new Node(Integer.parseInt(st.nextToken()));
                if (head == null) {
                    head = tail = node;
                } else {
                    tail.next = node;
                    tail = node;
                }
            }

            // Reverse
            Node prev = null;
            Node cur = head;
            while (cur != null) {
                Node nxt = cur.next;
                cur.next = prev;
                prev = cur;
                cur = nxt;
            }
            head = prev;

            StringBuilder out = new StringBuilder();
            boolean first = true;
            cur = head;
            while (cur != null) {
                if (!first) out.append(" ");
                out.append(cur.val);
                first = false;
                cur = cur.next;
            }

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
