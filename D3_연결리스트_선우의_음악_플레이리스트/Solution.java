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
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            Node head = null, tail = null;
            for (int i = 0; i < n; i++) {
                Node node = new Node(Integer.parseInt(st.nextToken()));
                if (head == null) {
                    head = tail = node;
                } else {
                    tail.next = node;
                    node.prev = tail;
                    tail = node;
                }
            }

            Node cur = head;
            int count = n;

            for (int i = 0; i < m; i++) {
                String cmdLine = br.readLine().trim();
                StringTokenizer cmdSt = new StringTokenizer(cmdLine);
                String cmd = cmdSt.nextToken();

                if (cmd.equals("NEXT")) {
                    if (cur.next != null) cur = cur.next;
                } else if (cmd.equals("PREV")) {
                    if (cur.prev != null) cur = cur.prev;
                } else if (cmd.equals("ADD_AFTER")) {
                    int x = Integer.parseInt(cmdSt.nextToken());
                    Node newNode = new Node(x);
                    newNode.next = cur.next;
                    newNode.prev = cur;
                    if (cur.next != null) cur.next.prev = newNode;
                    else tail = newNode;
                    cur.next = newNode;
                    count++;
                } else if (cmd.equals("REMOVE_CURRENT")) {
                    if (count > 1) {
                        Node p = cur.prev;
                        Node nxt = cur.next;
                        if (p != null) p.next = nxt;
                        else head = nxt;
                        if (nxt != null) nxt.prev = p;
                        else tail = p;

                        if (nxt != null) cur = nxt;
                        else cur = p;
                        count--;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append(" ").append(cur.id).append("\n");
        }
        System.out.print(sb);
    }
}
