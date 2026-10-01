import java.io.*;
import java.util.*;

public class Solution {
    static StringBuilder moves;

    static void hanoi(int n, int from, int mid, int to) {
        if (n == 1) {
            moves.append(from).append(" ").append(to).append("\n");
            return;
        }
        hanoi(n - 1, from, to, mid);
        moves.append(from).append(" ").append(to).append("\n");
        hanoi(n - 1, mid, from, to);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            int totalMoves = (1 << n) - 1;
            sb.append("#").append(tc).append(" ").append(totalMoves).append("\n");

            moves = new StringBuilder();
            hanoi(n, 1, 2, 3);
            sb.append(moves);
        }
        System.out.print(sb);
    }
}
