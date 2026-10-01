import java.io.*;
import java.util.*;

public class Solution {
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

            LinkedList<Integer> dq = new LinkedList<>();
            for (int i = 1; i <= n; i++) dq.add(i);

            st = new StringTokenizer(br.readLine());
            int totalRot = 0;

            for (int i = 0; i < m; i++) {
                int target = Integer.parseInt(st.nextToken());
                int idx = dq.indexOf(target);
                int leftDist = idx;
                int rightDist = dq.size() - idx;

                if (leftDist <= rightDist) {
                    totalRot += leftDist;
                    for (int k = 0; k < leftDist; k++) {
                        dq.addLast(dq.removeFirst());
                    }
                } else {
                    totalRot += rightDist;
                    for (int k = 0; k < rightDist; k++) {
                        dq.addFirst(dq.removeLast());
                    }
                }
                dq.removeFirst();
            }

            sb.append("#").append(tc).append(" ").append(totalRot).append("\n");
        }
        System.out.print(sb);
    }
}
