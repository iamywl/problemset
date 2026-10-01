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
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            Node headA = null, tailA = null;
            for (int i = 0; i < n; i++) {
                Node node = new Node(Integer.parseInt(st.nextToken()));
                if (headA == null) headA = tailA = node;
                else { tailA.next = node; tailA = node; }
            }

            st = new StringTokenizer(br.readLine());
            Node headB = null, tailB = null;
            for (int i = 0; i < m; i++) {
                Node node = new Node(Integer.parseInt(st.nextToken()));
                if (headB == null) headB = tailB = node;
                else { tailB.next = node; tailB = node; }
            }

            Node dummy = new Node(0);
            Node tail = dummy;
            Node curA = headA, curB = headB;

            while (curA != null && curB != null) {
                if (curA.val <= curB.val) {
                    tail.next = curA;
                    curA = curA.next;
                } else {
                    tail.next = curB;
                    curB = curB.next;
                }
                tail = tail.next;
            }
            if (curA != null) tail.next = curA;
            if (curB != null) tail.next = curB;

            StringBuilder out = new StringBuilder();
            boolean first = true;
            Node cur = dummy.next;
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
