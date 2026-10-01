import java.io.*;
import java.util.*;

public class Solution {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        Node head;
        int count;

        void insertFront(int x) {
            Node newNode = new Node(x);
            newNode.next = head;
            head = newNode;
            count++;
        }

        void insertBack(int x) {
            Node newNode = new Node(x);
            if (head == null) {
                head = newNode;
            } else {
                Node cur = head;
                while (cur.next != null) cur = cur.next;
                cur.next = newNode;
            }
            count++;
        }

        void insertAt(int k, int x) {
            if (k <= 0 || head == null) {
                insertFront(x);
                return;
            }
            if (k >= count) {
                insertBack(x);
                return;
            }
            Node cur = head;
            for (int i = 0; i < k - 1; i++) {
                cur = cur.next;
            }
            Node newNode = new Node(x);
            newNode.next = cur.next;
            cur.next = newNode;
            count++;
        }

        int deleteAt(int k) {
            if (k < 0 || k >= count || head == null) return -1;
            if (k == 0) {
                int val = head.data;
                head = head.next;
                count--;
                return val;
            }
            Node cur = head;
            for (int i = 0; i < k - 1; i++) {
                cur = cur.next;
            }
            int val = cur.next.data;
            cur.next = cur.next.next;
            count--;
            return val;
        }

        int getAt(int k) {
            if (k < 0 || k >= count || head == null) return -1;
            Node cur = head;
            for (int i = 0; i < k; i++) {
                cur = cur.next;
            }
            return cur.data;
        }

        int size() {
            return count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            SinglyLinkedList list = new SinglyLinkedList();
            StringBuilder out = new StringBuilder();
            boolean first = true;

            for (int i = 0; i < n; i++) {
                String cmdLine = br.readLine().trim();
                StringTokenizer st = new StringTokenizer(cmdLine);
                String cmd = st.nextToken();

                if (cmd.equals("insert_front")) {
                    int x = Integer.parseInt(st.nextToken());
                    list.insertFront(x);
                } else if (cmd.equals("insert_back")) {
                    int x = Integer.parseInt(st.nextToken());
                    list.insertBack(x);
                } else if (cmd.equals("insert_at")) {
                    int k = Integer.parseInt(st.nextToken());
                    int x = Integer.parseInt(st.nextToken());
                    list.insertAt(k, x);
                } else if (cmd.equals("delete_at")) {
                    int k = Integer.parseInt(st.nextToken());
                    int val = list.deleteAt(k);
                    if (!first) out.append(" ");
                    out.append(val);
                    first = false;
                } else if (cmd.equals("get_at")) {
                    int k = Integer.parseInt(st.nextToken());
                    int val = list.getAt(k);
                    if (!first) out.append(" ");
                    out.append(val);
                    first = false;
                } else if (cmd.equals("size")) {
                    if (!first) out.append(" ");
                    out.append(list.size());
                    first = false;
                }
            }

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
