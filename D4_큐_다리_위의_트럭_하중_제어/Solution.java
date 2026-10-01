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
            int w = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            int[] trucks = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                trucks[i] = Integer.parseInt(st.nextToken());
            }

            Deque<Integer> bridge = new ArrayDeque<>();
            for (int i = 0; i < w; i++) {
                bridge.offer(0);
            }

            int time = 0;
            int currentWeight = 0;
            int truckIdx = 0;

            while (truckIdx < n) {
                time++;
                currentWeight -= bridge.poll();

                if (currentWeight + trucks[truckIdx] <= l) {
                    bridge.offer(trucks[truckIdx]);
                    currentWeight += trucks[truckIdx];
                    truckIdx++;
                } else {
                    bridge.offer(0);
                }
            }

            time += w; // remaining trucks on bridge cross
            sb.append("#").append(tc).append(" ").append(time).append("\n");
        }
        System.out.print(sb);
    }
}
