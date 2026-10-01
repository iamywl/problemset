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
            String nLine = br.readLine();
            while (nLine != null && nLine.trim().isEmpty()) {
                nLine = br.readLine();
            }
            if (nLine == null) break;
            int n = Integer.parseInt(nLine.trim());

            Map<String, Integer> freqMap = new HashMap<>(n * 2);
            StringTokenizer st = null;
            for (int i = 0; i < n; i++) {
                while (st == null || !st.hasMoreTokens()) {
                    String wordsLine = br.readLine();
                    if (wordsLine == null) break;
                    st = new StringTokenizer(wordsLine);
                }
                if (!st.hasMoreTokens()) break;
                String word = st.nextToken();
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            }

            String bestWord = "";
            int maxFreq = -1;

            for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
                String word = entry.getKey();
                int count = entry.getValue();

                if (count > maxFreq) {
                    maxFreq = count;
                    bestWord = word;
                } else if (count == maxFreq) {
                    if (bestWord.isEmpty() || word.compareTo(bestWord) < 0) {
                        bestWord = word;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(bestWord).append(" ").append(maxFreq).append("\n");
        }
        System.out.print(sb);
    }
}
