import java.io.*;
import java.util.*;

public class Solution {
    static class Node {
        int id;
        Node next;
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
                prev = node;
            }
            prev.next = head; // 원형 연결

            long eliminatedSum = 0;
            Node curPrev = prev; // 삭제 직전 노드 추적

            for (int step = 0; step < n - 1; step++) {
                for (int i = 0; i < k - 1; i++) {
                    curPrev = curPrev.next;
                }
                Node target = curPrev.next;
                eliminatedSum += target.id;
                curPrev.next = target.next; // 탈락
            }

            int lastSurvivor = curPrev.id;

            sb.append("#").append(tc).append(" ").append(lastSurvivor).append(" ").append(eliminatedSum).append("\n");
        }
        System.out.print(sb);
    }
}
