import java.io.*;
import java.util.*;

public class Solution {
    static class Node {
        int id;
        int val;
        Node(int id, int val) {
            this.id = id;
            this.val = val;
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
            PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
            PriorityQueue<Node> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.val, a.val));
            boolean[] valid = new boolean[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int x = Integer.parseInt(st.nextToken());

                if (cmd.equals("I")) {
                    Node node = new Node(i, x);
                    minHeap.offer(node);
                    maxHeap.offer(node);
                    valid[i] = true;
                } else if (cmd.equals("D")) {
                    if (x == 1) {
                        clean(maxHeap, valid);
                        if (!maxHeap.isEmpty()) {
                            valid[maxHeap.poll().id] = false;
                        }
                    } else {
                        clean(minHeap, valid);
                        if (!minHeap.isEmpty()) {
                            valid[minHeap.poll().id] = false;
                        }
                    }
                }
            }

            clean(maxHeap, valid);
            clean(minHeap, valid);

            sb.append("#").append(tc).append(" ");
            if (maxHeap.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(maxHeap.peek().val).append(" ").append(minHeap.peek().val).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void clean(PriorityQueue<Node> pq, boolean[] valid) {
        while (!pq.isEmpty() && !valid[pq.peek().id]) {
            pq.poll();
        }
    }
}
