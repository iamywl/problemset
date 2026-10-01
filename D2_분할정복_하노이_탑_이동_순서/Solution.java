import java.io.*;
import java.util.*;

public class Solution {
    static List<String> moves;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            moves = new ArrayList<>();
            hanoi(n, 1, 2, 3);
            
            long total = (1L << n) - 1;
            sb.append("#").append(tc).append(" ").append(total);
            int printLimit = Math.min(3, moves.size());
            for (int i = 0; i < printLimit; i++) {
                sb.append(" ").append(moves.get(i));
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    
    private static void hanoi(int n, int src, int aux, int dst) {
        if (n == 0) return;
        if (moves.size() >= 3) return; // only need first 3 moves
        hanoi(n - 1, src, dst, aux);
        if (moves.size() < 3) {
            moves.add(src + "-" + dst);
        }
        hanoi(n - 1, aux, src, dst);
    }
}
