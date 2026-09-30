import java.io.*;
import java.util.*;

public class Solution {
    static int N, R;
    static char[] chars;
    static boolean[] visited;
    static int count;

    static boolean isVowel(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            chars = new char[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) chars[i] = st.nextToken().charAt(0);

            visited = new boolean[N];
            count = 0;
            perm(0);

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }

    static void perm(int depth) {
        if (depth == R) {
            count++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                if (depth == 0 && !isVowel(chars[i])) continue;
                visited[i] = true;
                perm(depth + 1);
                visited[i] = false;
            }
        }
    }
}
