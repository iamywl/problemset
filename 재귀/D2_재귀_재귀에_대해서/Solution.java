import java.io.*;
import java.util.*;

public class Solution {
    static StringBuilder traceSb;

    static void printTrace(int n) {
        if (n <= 0) return;
        traceSb.append(n).append(" ");
        printTrace(n - 1);
    }

    static long recSum(int n) {
        if (n <= 1) return n;
        return n + recSum(n - 1);
    }

    static long recFact(int n) {
        if (n <= 1) return 1;
        return n * recFact(n - 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            traceSb = new StringBuilder();
            printTrace(n);

            long sum = recSum(n);
            long fact = recFact(n);

            sb.append("#").append(tc).append(" ").append(traceSb.toString()).append(sum).append(" ").append(fact).append("\n");
        }
        System.out.print(sb);
    }
}
