import java.io.*;
import java.util.*;

public class Solution {
    static class Node implements Comparable<Node> {
        int r, c, cost;
        Node(int r, int c, int cost) { this.r = r; this.c = c; this.cost = cost; }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            int[][] map = new int[n][n];

            for (int r = 0; r < n; r++) {
                String row = br.readLine().trim();
                for (int c = 0; c < n; c++) {
                    map[r][c] = row.charAt(c) - '0';
                }
            }

            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

            PriorityQueue<Node> pq = new PriorityQueue<>();
            dist[0][0] = 0;
            pq.offer(new Node(0, 0, 0));

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if (cur.r == n - 1 && cur.c == n - 1) break;
                if (cur.cost > dist[cur.r][cur.c]) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                        if (dist[cur.r][cur.c] + map[nr][nc] < dist[nr][nc]) {
                            dist[nr][nc] = dist[cur.r][cur.c] + map[nr][nc];
                            pq.offer(new Node(nr, nc, dist[nr][nc]));
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dist[n - 1][n - 1]).append("\n");
        }
        System.out.print(sb);
    }
}
