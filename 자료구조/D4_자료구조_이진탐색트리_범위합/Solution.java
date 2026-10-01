import java.io.*;
import java.util.*;

public class Solution {
    static class Node {
        long key;
        Node left, right;
        Node(long key) {
            this.key = key;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            String header = br.readLine();
            while (header != null && header.trim().isEmpty()) {
                header = br.readLine();
            }
            if (header == null) break;

            StringTokenizer st = new StringTokenizer(header);
            int n = Integer.parseInt(st.nextToken());
            long L = Long.parseLong(st.nextToken());
            long R = Long.parseLong(st.nextToken());

            st = null;
            Node root = null;

            for (int i = 0; i < n; i++) {
                while (st == null || !st.hasMoreTokens()) {
                    String row = br.readLine();
                    if (row == null) break;
                    st = new StringTokenizer(row);
                }
                if (!st.hasMoreTokens()) break;
                long key = Long.parseLong(st.nextToken());
                root = insert(root, key);
            }

            long sum = rangeSum(root, L, R);
            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }
        System.out.print(sb);
    }

    private static Node insert(Node root, long key) {
        if (root == null) {
            return new Node(key);
        }
        Node curr = root;
        while (true) {
            if (key < curr.key) {
                if (curr.left == null) {
                    curr.left = new Node(key);
                    break;
                }
                curr = curr.left;
            } else if (key > curr.key) {
                if (curr.right == null) {
                    curr.right = new Node(key);
                    break;
                }
                curr = curr.right;
            } else {
                // Duplicate key: ignore
                break;
            }
        }
        return root;
    }

    private static long rangeSum(Node node, long L, long R) {
        if (node == null) return 0;
        if (node.key < L) {
            return rangeSum(node.right, L, R);
        }
        if (node.key > R) {
            return rangeSum(node.left, L, R);
        }
        return node.key + rangeSum(node.left, L, R) + rangeSum(node.right, L, R);
    }
}
