import java.io.*;
import java.util.*;

public class Solution {
    static class Node {
        int key, val;
        Node prev, next;
        Node(int key, int val) { this.key = key; this.val = val; }
    }

    static class LRUCache {
        int capacity;
        Map<Integer, Node> map;
        Node head, tail;

        LRUCache(int cap) {
            this.capacity = cap;
            map = new HashMap<>();
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        void insertToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node node = map.get(key);
            remove(node);
            insertToHead(node);
            return node.val;
        }

        void put(int key, int val) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.val = val;
                remove(node);
                insertToHead(node);
            } else {
                if (map.size() >= capacity) {
                    Node lru = tail.prev;
                    remove(lru);
                    map.remove(lru.key);
                }
                Node newNode = new Node(key, val);
                insertToHead(newNode);
                map.put(key, newNode);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cap = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            LRUCache cache = new LRUCache(cap);
            StringBuilder out = new StringBuilder();
            boolean first = true;

            for (int i = 0; i < m; i++) {
                String cmdLine = br.readLine().trim();
                StringTokenizer cmdSt = new StringTokenizer(cmdLine);
                String cmd = cmdSt.nextToken();

                if (cmd.equals("PUT")) {
                    int k = Integer.parseInt(cmdSt.nextToken());
                    int v = Integer.parseInt(cmdSt.nextToken());
                    cache.put(k, v);
                } else if (cmd.equals("GET")) {
                    int k = Integer.parseInt(cmdSt.nextToken());
                    int val = cache.get(k);
                    if (!first) out.append(" ");
                    out.append(val);
                    first = false;
                }
            }

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
